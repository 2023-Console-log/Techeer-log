package com.techeerlog.dummy.service;

import com.techeerlog.comment.domain.Comment;
import com.techeerlog.comment.repository.CommentRepository;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DummyService {
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final CommentRepository commentRepository;
    private List<Member> members;

    Faker faker = new Faker();

    @Transactional
    public void insertDummy(){
        members = generateDummyMembers(100);
        memberRepository.saveAll(members);

        List<Project> projects = generateDummyProjects(1);
        projectRepository.saveAll(projects);

        Project project = projects.get(0);

        List<Comment> comments = generateDummyComments(10000, project);
        commentRepository.saveAll(comments);

        project.setCommentList(comments);
        projectRepository.save(project);
    }

    private List<Comment> generateDummyComments(int count, Project project){
        return Stream.generate(() -> createDummyComment(members.get(faker.random().nextInt(members.size())), project))  // Generate a random project
                .limit(count)
                .collect(Collectors.toList());
    }

    private Comment createDummyComment(Member member, Project project){
        return Comment.builder()
                .project(project)
                .member(member)
                .message(faker.lorem().sentence())
                .build();
    }

    private List<Project> generateDummyProjects(int count) {
        if (members.isEmpty()) {
            throw new IllegalStateException("No members found. Please add some members first.");
        }

        return Stream.generate(() -> createDummyProject(members.get(faker.random().nextInt(members.size()))))  // Generate a random project
                .limit(count)
                .collect(Collectors.toList());
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
        AtomicInteger counter = new AtomicInteger(0);
        return faker.collection(() -> createDummyMember(counter.incrementAndGet())) // count 증가
                .maxLen(count)
                .generate();
    }

    private Member createDummyMember(int count) {
        return Member.builder()
                .loginId(new LoginId(String.valueOf(count)))
                .password(new Password(faker.internet().password()))
                .nickname(new Nickname(String.valueOf(count)))
                .profileImageUrl(faker.internet().url())
                .build();
    }


}
