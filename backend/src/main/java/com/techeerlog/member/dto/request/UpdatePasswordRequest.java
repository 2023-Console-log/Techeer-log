package com.techeerlog.member.dto.request;

import lombok.Getter;

@Getter
public class UpdatePasswordRequest {
    String currentPassword;
    String newPassword;
}
