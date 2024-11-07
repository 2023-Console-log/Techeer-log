package com.techeerlog.dummy.service;

import com.techeerlog.comment.repository.CommentRepository;
import com.techeerlog.framework.repository.FrameworkRepository;
import com.techeerlog.member.domain.LoginId;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.domain.Nickname;
import com.techeerlog.member.domain.Password;
import com.techeerlog.member.exception.InvalidNicknameException;
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
    private List<Member> members;

    Faker faker = new Faker();

    @Transactional
    public void insertDummy(){
        int count = 2;
        members = generateDummyMembers(count);
        memberRepository.saveAll(members);

        List<Project> projects = generateDummyProjects(count);
        projectRepository.saveAll(projects);
    }

    private List<Project> generateDummyProjects(int count) {
        if (members.isEmpty()) {
            throw new IllegalStateException("No members found. Please add some members first.");
        }

        return Stream.generate(() -> createDummyProject(members.get(faker.random().nextInt(members.size()))))  // Generate a random project
                .limit(count)  // Limit the number of projects to 'count'
                .collect(Collectors.toList());  // Collect the results into a List
    }


    private Project createDummyProject(Member member) {
        return Project.builder()
                .mainImageUrl(faker.internet().image())
                .title(faker.book().title())
                .subtitle(faker.lorem().sentence())
                .content(faker.lorem().sentence())
                .startDate(LocalDate.now().minusMonths(faker.random().nextInt(1, 24)))
                .endDate(LocalDate.now().plusMonths(faker.random().nextInt(1, 12)))
                .platform(faker.options().option(PlatformEnum.class))
                .projectTypeEnum(faker.options().option(ProjectTypeEnum.class))
                .projectTeamNameEnum(faker.options().option(ProjectTeamNameEnum.class))
                .year(LocalDate.now().getYear())
                .semesterEnum(faker.options().option(SemesterEnum.class))
                .rankEnum(faker.options().option(RankEnum.class))
                .projectStatusEnum(faker.options().option(ProjectStatusEnum.class))
                .githubLink(faker.internet().url())
                .blogLink(faker.internet().url())
                .websiteLink(faker.internet().url())
                .member(member)
                .build();
    }

    private List<Member> generateDummyMembers(int count) {
        return faker.collection(this::createDummyMember)
                .maxLen(count)
                .generate();
    }

    private Member createDummyMember() {
        String nickName = validateNickname();

        return Member.builder()
                .loginId(new LoginId(faker.name().fullName()))
                .password(new Password(faker.internet().password()))
                .nickname(new Nickname(nickName))
                .profileImageUrl(faker.internet().url())
                .build();
    }

    private String validateNickname(){
        String nickname = null;

        while (nickname == null) {
            try {
                nickname = faker.funnyName().name();
                // Nickname 객체 생성 시 validate 호출
                new Nickname(nickname); // 유효하지 않으면 InvalidNicknameException 발생
            } catch (InvalidNicknameException e) {
                // 유효하지 않은 닉네임은 다시 생성하도록 처리
                nickname = null;
            }
        }

        return nickname;
    }

}
