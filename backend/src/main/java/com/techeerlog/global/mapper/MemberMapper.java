package com.techeerlog.global.mapper;

import com.techeerlog.member.domain.Member;
import com.techeerlog.member.dto.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberResponse memberToMemberResponse(Member member);
}
