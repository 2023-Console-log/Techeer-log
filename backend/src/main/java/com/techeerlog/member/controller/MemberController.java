package com.techeerlog.member.controller;

import com.techeerlog.auth.dto.AuthInfo;
import com.techeerlog.auth.service.RefreshTokenService;
import com.techeerlog.global.mapper.MemberMapper;
import com.techeerlog.global.response.ResultResponse;
import com.techeerlog.global.response.SimpleResultResponse;
import com.techeerlog.global.support.token.Login;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.dto.request.EditMemberRequest;
import com.techeerlog.member.dto.request.SignupRequest;
import com.techeerlog.member.dto.request.UpdatePasswordRequest;
import com.techeerlog.member.dto.response.MemberResponse;
import com.techeerlog.member.dto.response.ProfileResponse;
import com.techeerlog.member.service.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.techeerlog.global.response.ResultCode.*;


@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberMapper memberMapper;
    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "회원가입", description = "회원가입 기능")
    @PostMapping(value = "/signup")
    public ResponseEntity<ResultResponse<MemberResponse>> signUp(@RequestBody SignupRequest signupRequest) {
        Member memberResponse = memberServiceImpl.signUp(signupRequest);
        ResultResponse<MemberResponse> resultResponse = new ResultResponse<>(SIGNUP_SUCCESS, memberMapper.memberToMemberResponse(memberResponse));

        return ResponseEntity.status(SIGNUP_SUCCESS.getStatus())
                .body(resultResponse);
    }

    @Operation(summary = "프로필 조회", description = "프로필 조회 기능")
    @GetMapping("/profile")
    public ResponseEntity<ResultResponse<ProfileResponse>> findProfile(@Login AuthInfo authInfo) {
        ProfileResponse profileResponse = memberServiceImpl.findProfile(authInfo);
        ResultResponse<ProfileResponse> resultResponse = new ResultResponse<>(FIND_PROFILE_SUCCESS, profileResponse);

        return ResponseEntity.status(FIND_PROFILE_SUCCESS.getStatus())
                .body(resultResponse);
    }

    @Operation(summary = "프로필 수정", description = "프로필 수정 기능")
    @PatchMapping(consumes = "multipart/form-data")
    public ResponseEntity<SimpleResultResponse> editMember(@RequestPart(value = "data") EditMemberRequest editMemberRequest,
                                                           @RequestPart(value = "part", required = false) MultipartFile multipartFile,
                                                           @Login AuthInfo authInfo) {

        memberServiceImpl.editMemberInfo(editMemberRequest, authInfo, multipartFile);
        refreshTokenService.deleteToken(authInfo.getId());

        SimpleResultResponse resultResponse = new SimpleResultResponse(EDIT_PROFILE_SUCCESS);

        return ResponseEntity.status(EDIT_PROFILE_SUCCESS.getStatus())
                .body(resultResponse);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경 기능")
    @PatchMapping("/password")
    public ResponseEntity<SimpleResultResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,
                                                               @Login AuthInfo authInfo) {
        memberServiceImpl.updatePassword(authInfo, updatePasswordRequest);
        refreshTokenService.deleteToken(authInfo.getId());
        SimpleResultResponse resultResponse = new SimpleResultResponse(UPDATE_CODE_SUCCESS);

        return ResponseEntity.status(UPDATE_CODE_SUCCESS.getStatus())
                .body(resultResponse);
    }
}
