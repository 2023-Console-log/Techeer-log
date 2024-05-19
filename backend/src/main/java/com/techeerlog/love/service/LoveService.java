package com.techeerlog.love.service;

import com.techeerlog.auth.dto.AuthInfo;
import com.techeerlog.global.support.UtilMethod;
import com.techeerlog.love.domain.Love;
import com.techeerlog.love.dto.LoveResponse;
import com.techeerlog.love.exception.LoveNotFoundException;
import com.techeerlog.love.repository.LoveRepository;
import com.techeerlog.member.domain.Member;
import com.techeerlog.project.domain.Project;
import com.techeerlog.project.exception.ProjectNotFoundException;
import com.techeerlog.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoveService {
    private final LoveRepository loveRepository;
    private final ProjectRepository projectRepository;
    private final UtilMethod utilMethod;


    public LoveService(LoveRepository loveRepository, ProjectRepository projectRepository,
                       UtilMethod utilMethod) {

        this.loveRepository = loveRepository;
        this.projectRepository = projectRepository;
        this.utilMethod = utilMethod;
    }

    public LoveResponse addLove(Long projectId, AuthInfo authInfo) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        Member member = utilMethod.findMemberByAuthInfo(authInfo);

        // 좋아요 중복 체크
        if (loveRepository.existsByMemberIdAndProjectId(member.getId(), project.getId())) {
            throw new LoveNotFoundException();
        }
        Love love = Love.builder()
                .project(project)
                .member(member)
                .build();
        loveRepository.save(love);

        return new LoveResponse(love.getId(), project.getId());
    }


    public LoveResponse deleteLove(Long projectId, AuthInfo authInfo) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        Member member = utilMethod.findMemberByAuthInfo(authInfo);

        Optional<Love> love = loveRepository.findByMemberIdAndProjectId(member.getId(), project.getId());
        if (love.isEmpty()) {
            throw new LoveNotFoundException();
        }

        Long loveId = love.get().getId();
        loveRepository.delete(love.get());
        return new LoveResponse(loveId, project.getId());
    }
}