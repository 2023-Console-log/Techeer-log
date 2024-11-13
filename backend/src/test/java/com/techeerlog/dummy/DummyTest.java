package com.techeerlog.dummy;

import com.techeerlog.comment.domain.Comment;
import com.techeerlog.member.domain.LoginId;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.domain.Nickname;
import com.techeerlog.member.domain.Password;
import com.techeerlog.member.exception.InvalidNicknameException;
import com.techeerlog.project.domain.Project;
import com.techeerlog.project.enums.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DummyTest {
    Faker faker = new Faker();

    @Test
    void fakerTest() {
        int count = 2;
        System.out.println("members");
        List<Member> members = generateDummyMembers(count);
        for (Member member : members) {
            System.out.println(member.getNickname());
            System.out.println(member.getProfileImageUrl());
            System.out.println("---------");
        }
        System.out.println("projects");
        List<Project> projects = generateDummyProjects(count, members);
        for (Project project : projects) {
            System.out.println(project.getTitle());
            System.out.println("---------");

        }

        System.out.println("comments");
        List<Comment> comments = generateDummyComments(count, projects.get(0), members);
        for (Comment comment : comments) {
            System.out.println(comment.getMessage());
            System.out.println(comment.getMember().getNickname());
            System.out.println(comment.getProject().getTitle());
            System.out.println("---------");
        }

    }

    private List<Comment> generateDummyComments(int count, Project project, List<Member> members){
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

    private List<Project> generateDummyProjects(int count, List<Member> members) {
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
                .content(faker.lorem().paragraph())
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
