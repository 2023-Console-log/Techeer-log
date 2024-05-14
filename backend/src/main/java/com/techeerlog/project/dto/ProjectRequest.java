package com.techeerlog.project.dto;

import com.techeerlog.framework.dto.FrameworkRequest;
import com.techeerlog.project.enums.PlatformEnum;
import com.techeerlog.project.enums.ProjectStatusEnum;
import com.techeerlog.project.enums.ProjectTypeEnum;
import com.techeerlog.project.enums.SemesterEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private String title;
    private String subtitle;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private PlatformEnum platform;
    private ProjectTypeEnum projectType;
    private int year;
    private SemesterEnum semester;
    private ProjectStatusEnum projectStatus;
    private String githubLink;
    private String blogLink;
    private String websiteLink;
    private String mainImageUrl;
    private List<ProjectMemberRequest> projectMemberRequestList;
    private List<NonRegisterProjectMemberRequest> nonRegisterProjectMemberRequestList;
    private List<FrameworkRequest> frameworkRequestList;
}