package com.techeerlog.dummy.service;

import com.techeerlog.comment.repository.CommentRepository;
import com.techeerlog.framework.repository.FrameworkRepository;
import com.techeerlog.member.domain.LoginId;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.domain.Nickname;
import com.techeerlog.member.domain.Password;
import com.techeerlog.member.repository.MemberRepository;
import com.techeerlog.project.domain.Project;
import com.techeerlog.project.enums.*;
import com.techeerlog.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DummyService {
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final FrameworkRepository frameworkRepository;
    private final CommentRepository commentRepository;

    Faker faker = new Faker();

    @Transactional
    public void insertDummy(){
        List<Member> members = generateDummyMembers(2);
        memberRepository.saveAll(members);
    }

    private List<Member> generateDummyMembers(int count) {
        return faker.collection(this::createDummyMember)
                .maxLen(count)
                .generate();
    }

    private Member createDummyMember() {
        return Member.builder()
                .loginId(new LoginId(faker.name().fullName()))
                .password(new Password(faker.internet().password()))
                .nickname(new Nickname(faker.funnyName().name()))
                .profileImageUrl(faker.internet().url())
                .build();
    }

}
