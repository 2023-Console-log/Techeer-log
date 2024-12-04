package com.techeerlog.member.repository;

import com.techeerlog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByLoginId(String loginId);

    boolean existsMemberByNickname(String nickname);

    Optional<Member> findByLoginIdAndPassword(String loginId, String password);
}
