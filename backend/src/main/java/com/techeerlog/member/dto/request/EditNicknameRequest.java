package com.techeerlog.member.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EditNicknameRequest {

    @NotBlank(message = "닉네임은 1자 이상이어야 합니다.")
    private String nickname;

    public EditNicknameRequest() {
    }

    public EditNicknameRequest(String nickname) {
        this.nickname = nickname;
    }
}
