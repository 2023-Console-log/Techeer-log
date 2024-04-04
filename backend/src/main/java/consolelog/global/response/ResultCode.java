package consolelog.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // Auth
    LOGIN_SUCCESS("A001", 200, "로그인 성공"),
    REFRESH_SUCCESS("A002", 204, "access token 재발급 성공"),
    LOGOUT_SUCCESS("A003", 200, "로그아웃 성공"),

    // Member
    SIGNUP_SUCCESS("M001", 201, "회원가입 성공"),
    FIND_PROFILE_SUCCESS("MOO2", 200, "프로필 조회 성공"),

    // Post
    FIND_POST_SUCCESS("P001", 200, "로그인 상태 게시글 조회 성공"),
    ADD_POST_SUCCESS("P002", 201, "게시글 등록 성공"),
    UPDATE_POST_SUCCESS("P003", 201, "게시글 수정 성공"),
    DELETE_SUCCESS("P004", 200, "게시글 삭제 성공"),
    FIND_POST_LIST_SUCCESS("P005", 200, "게시글 리스트 조회 성공"),

    // Comment
    COMMENT_CREATED_SUCCESS("C001", 201, "댓글 등록 성공"),
    COMMENT_REPLY_CREATED_SUCCESS("C002", 201, "대댓글 등록 성공"),
    UPDATE_COMMENT_SUCCESS("C003", 201, "댓글 수정 성공"),
    DELETE_COMMENT_SUCCESS("C004", 200, "댓글 삭제 성공"),
    GET_COMMENT_SUCCESS("C005", 200, "댓글 조회 성공"),

    // Like
    LIKE_CREATED_SUCCESS("L001", 201, "좋아요 성공"),

    // Image
    UPLOAD_SUCCESS("I001", 200, "이미지 업로드 성공"),

    ;


    private final String code;
    private final int status;
    private final String message;
}
