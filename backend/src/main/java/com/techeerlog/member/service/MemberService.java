package com.techeerlog.member.service;

import com.techeerlog.auth.dto.AuthInfo;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.dto.request.EditMemberRequest;
import com.techeerlog.member.dto.request.SignupRequest;
import com.techeerlog.member.dto.request.UpdatePasswordRequest;
import com.techeerlog.member.dto.response.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;


public interface MemberService {
    Member signUp(SignupRequest signupRequest);
    ProfileResponse findProfile(AuthInfo authInfo);
    void updatePassword(AuthInfo authInfo, UpdatePasswordRequest updatePasswordRequest);
    void editMemberInfo(EditMemberRequest editMemberRequest, AuthInfo authInfo, MultipartFile multipartFile);
}
