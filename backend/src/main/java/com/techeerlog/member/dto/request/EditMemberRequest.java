package com.techeerlog.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditMemberRequest {
    private String nickname;
    private String introduction;
}
