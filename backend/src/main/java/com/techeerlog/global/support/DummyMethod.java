package com.techeerlog.global.support;

import com.techeerlog.framework.domain.Framework;
import com.techeerlog.framework.enums.FrameworkTypeEnum;
import com.techeerlog.framework.repository.FrameworkRepository;
import com.techeerlog.member.domain.LoginId;
import com.techeerlog.member.domain.Member;
import com.techeerlog.member.domain.Nickname;
import com.techeerlog.member.domain.Password;
import com.techeerlog.member.repository.MemberRepository;
import com.techeerlog.project.domain.Project;
import com.techeerlog.project.enums.*;
import com.techeerlog.project.repository.ProjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Component
public class DummyMethod {

    @Autowired
    private FrameworkRepository frameworkRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PostConstruct
    public void init() {
        createFrameworks();
        createMembers();
        createProjects();
    }

    private void createFrameworks() {
        List<Framework> frameworks = Arrays.asList(
                new Framework(1L, "Spring", FrameworkTypeEnum.BACKEND),
                new Framework(2L, "Django", FrameworkTypeEnum.BACKEND),
                new Framework(3L, "Angular", FrameworkTypeEnum.FRONTEND)
        );
        frameworkRepository.saveAll(frameworks);
    }

    private void createMembers() {
        List<Member> members = Arrays.asList(
                new Member(1L, new LoginId("test1"), new Password("1234"), new Nickname("test1"), "profileImageUrl1"),
                new Member(2L, new LoginId("test2"), new Password("1234"), new Nickname("test2"), "profileImageUrl2"),
                new Member(3L, new LoginId("test3"), new Password("1234"), new Nickname("test3"), "profileImageUrl3"),
                new Member(4L, new LoginId("test4"), new Password("1234"), new Nickname("test4"), "profileImageUrl4")
        );
        memberRepository.saveAll(members);
    }

    private void createProjects() {
        Member member = memberRepository.findById(1L).orElse(null);

        List<Project> projects = new ArrayList<>();
        Long i = 1L;
        for (; i <= 50; i++) {
            projects.add(new Project(
                    i, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.PERSONAL_PROJECT, ProjectTeamNameEnum.A,2021, SemesterEnum.FIRST, RankEnum.NONE,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
            ));
        }
        projects.add(
                new Project(
                        i++, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.BOOTCAMP, ProjectTeamNameEnum.B,2023, SemesterEnum.FIRST, RankEnum.FIRST,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                )
        );
        projects.add(
                new Project(
                        i++, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.BOOTCAMP, ProjectTeamNameEnum.C, 2023, SemesterEnum.FIRST, RankEnum.SECOND,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                )
        );
        projects.add(
                new Project(
                        i++, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.BOOTCAMP, ProjectTeamNameEnum.D, 2023, SemesterEnum.FIRST, RankEnum.THIRD,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                )
        );

        projects.add(
                new Project(
                        i++, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.BOOTCAMP, ProjectTeamNameEnum.A, 2023, SemesterEnum.SECOND, RankEnum.FIRST,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                )
        );
        projects.add(
                new Project(
                        i++, "mainImageUrl1", "title1", "subtitle1", "content1", LocalDate.now(), LocalDate.now(), PlatformEnum.WEB, ProjectTypeEnum.BOOTCAMP, ProjectTeamNameEnum.A, 2023, SemesterEnum.SECOND, RankEnum.SECOND,ProjectStatusEnum.COMPLETED, "githubLink1", "blogLink1", "websiteLink1", member, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
                )
        );
        projectRepository.saveAll(projects);
    }
}