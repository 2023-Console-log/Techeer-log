package com.techeerlog.member.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String loginId;
    private String nickname;
    private String profileImageUrl;
}
