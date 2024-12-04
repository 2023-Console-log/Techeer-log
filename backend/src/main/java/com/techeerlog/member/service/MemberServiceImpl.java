package com.techeerlog.member.service;

import com.techeerlog.auth.domain.encryptor.EncryptorI;
import com.techeerlog.auth.dto.AuthInfo;
import com.techeerlog.global.config.BaseEntity;
import com.techeerlog.image.service.AmazonS3Service;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.dto.request.EditMemberRequest;
import com.techeerlog.member.dto.request.SignupRequest;
import com.techeerlog.member.dto.request.UpdatePasswordRequest;
import com.techeerlog.member.dto.response.ProfileResponse;
import com.techeerlog.member.exception.*;
import com.techeerlog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl extends BaseEntity implements MemberService {

    private static final String DEFAULT_PROFILE_IMAGE_URL = "https://techeer-bucket.s3.ap-northeast-2.amazonaws.com/image+(3).png";
    private final MemberRepository memberRepository;
    private final EncryptorI encryptor;
    private final AmazonS3Service amazonS3Service;

    @Transactional
    public Member signUp(SignupRequest signupRequest) {
        validate(signupRequest);

        Member member = Member.builder()
                .loginId(signupRequest.getLoginId())
                .password(encryptPassword(signupRequest.getPassword()))
                .profileImageUrl(DEFAULT_PROFILE_IMAGE_URL)
                .nickname(signupRequest.getNickname())
                .build();
        memberRepository.save(member);
        return member;
    }

    public ProfileResponse findProfile(AuthInfo authInfo) {
        Member member = memberRepository.findById(authInfo.getId())
                .orElseThrow(MemberNotFoundException::new);
        return ProfileResponse.of(member);
    }

    @Transactional
    public void editMemberInfo(EditMemberRequest editMemberRequest, AuthInfo authInfo, MultipartFile multipartFile) {
        Member member = findMemberById(authInfo.getId());

        updateProfileImage(member, multipartFile);

        if (editMemberRequest != null) {
            updateNickname(member, editMemberRequest.getNickname());
            updateIntroduction(member, editMemberRequest.getIntroduction());
        }
    }

    @Transactional
    public void updatePassword(AuthInfo authInfo, UpdatePasswordRequest updatePasswordRequest) {
        // 기존 비밀번호를 찾기
        Member member = memberRepository.findById(authInfo.getId())
                .orElseThrow(MemberNotFoundException::new);
        String password = member.getPassword();

        // 기존 비밀번호와 요청으로 입력된 비밀번호가 일치한지 확인
        String currentPassword = updatePasswordRequest.getCurrentPassword();
        if (!password.equals(encryptor.encrypt(currentPassword))) {
            throw new IncorrectPasswordException();
        }

        // 일치하다면 새로운 비밀번호가 유효한지 확인
        String newPassword = updatePasswordRequest.getNewPassword();
//        Password.validate(newPassword);

        // 비밀번호 업데이트
        member.updatePassword(encryptPassword(newPassword));
        memberRepository.save(member);
    }

    private void validate(SignupRequest signupRequest) {
        confirmPassword(signupRequest.getPassword(), signupRequest.getPasswordConfirmation());
        validateUniqueNickname(signupRequest);
        validateUniqueLoginId(signupRequest);
    }

    private void validateUniqueLoginId(SignupRequest signupRequest) {
        boolean isDuplicatedLoginId = memberRepository
                .existsMemberByLoginId(signupRequest.getLoginId());
        if (isDuplicatedLoginId) {
            throw new InvalidLoginIdException();
        }
    }

    // else throw 넣어야 함
    private void validateUniqueNickname(SignupRequest signupRequest) {
        boolean isDuplicatedNickname = memberRepository
                .existsMemberByNickname(signupRequest.getNickname());
        if (isDuplicatedNickname) {
            throw new DuplicateNicknameException();
        }
    }

    private void confirmPassword(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new PasswordConfirmationException();
        }
    }


    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    private void updateProfileImage(Member member, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String profileImageUrl = amazonS3Service.upload(member.getNickname(), multipartFile);
            member.updateProfileImageUrl(profileImageUrl);
        }
    }

    private void updateNickname(Member member, String newNickname) {
        if (newNickname != null && !newNickname.isEmpty() && !newNickname.equals(member.getNickname())) {
            validateUniqueNickname(newNickname);
            member.updateNickname(newNickname);
        }
    }

    private void updateIntroduction(Member member, String newIntroduction) {
        if (newIntroduction != null && !newIntroduction.equals(member.getIntroduction())) {
            member.updateIntroduction(newIntroduction);
        }
    }

    private void validateUniqueNickname(String validNickname) {
        if (memberRepository.existsMemberByNickname(validNickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private String encryptPassword(String password) {
        return encryptor.encrypt(password);
    }
}
