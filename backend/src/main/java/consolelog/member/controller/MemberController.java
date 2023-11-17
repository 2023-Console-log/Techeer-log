package consolelog.member.controller;

import consolelog.auth.dto.AuthInfo;
import consolelog.global.result.ResultResponse;
import consolelog.image.service.AmazonS3Service;
import consolelog.member.domain.Member;
import consolelog.member.dto.MemberResponse;
import consolelog.member.dto.NicknameResponse;
import consolelog.member.dto.SignupRequest;
import consolelog.member.service.MemberService;
import consolelog.global.support.token.Login;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static consolelog.global.result.ResultCode.FIND_NICKNAME_SUCCESS;
import static consolelog.global.result.ResultCode.SIGNUP_SUCCESS;

@RestController
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원가입", description = "회원가입 기능")
    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<ResultResponse<SignupRequest>> signUp(@RequestPart("data") SignupRequest signupRequest,
                                                                @RequestPart("file") MultipartFile multipartFile) {
        Member member = memberService.signUp(signupRequest, multipartFile);
        ResultResponse<SignupRequest> resultResponse = new ResultResponse<>(SIGNUP_SUCCESS, new MemberResponse(member));
        return ResponseEntity.status(HttpStatus.CREATED).body(resultResponse);
    }

//    @GetMapping(value = "/signup/exists", params = "loginId")
//    public ResponseEntity<UniqueResponse> validateUniqueLoginId(@RequestParam String loginId) {
//        UniqueResponse uniqueResponse = memberService.checkUniqueLoginId(loginId);
//        return ResponseEntity.ok(uniqueResponse);
//    }
//
//    @GetMapping(value = "/signup/exists", params = "nickname")
//    public ResponseEntity<UniqueResponse> validateUniqueNickname(@RequestParam String nickname) {
//        UniqueResponse uniqueResponse = memberService.checkUniqueNickname(nickname);
//        return ResponseEntity.ok(uniqueResponse);
//    }

    @Operation(summary = "닉네임 조회", description = "닉네임 조회 기능")
    @GetMapping("/nickname")
    public ResponseEntity<ResultResponse<NicknameResponse>> findNickname(@Login AuthInfo authInfo) {
        NicknameResponse nicknameResponse = memberService.findNickname(authInfo);
        ResultResponse<NicknameResponse> resultResponse = new ResultResponse<>(FIND_NICKNAME_SUCCESS, nicknameResponse);
        return ResponseEntity.ok().body(resultResponse);
    }
//    @PatchMapping("/nickname")
//    public ResponseEntity<Void> editNickname(@ResponseBody EditNicknameRequest editNicknameRequest, @Login AuthInfo authInfo) {
//        memberService.editNickname(editNicknameRequest, authInfo);
//        return ResponseEntity.noContent()
//                .header(HttpHeaders.AUTHORIZATION,
//                        "Bearer" + tokenManager.createNewTokenWithNewNickName(
//                                nicknameUpdateRequest.getNickname(), authInfo)
//                )
//                .build();
//    }
}
