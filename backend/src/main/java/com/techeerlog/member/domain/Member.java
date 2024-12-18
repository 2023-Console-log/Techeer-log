package com.techeerlog.member.domain;

import com.techeerlog.global.config.BaseEntity;
import com.techeerlog.member.enums.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@SQLDelete(sql = "UPDATE member SET deleted = TRUE WHERE member_id = ?")
@SQLRestriction("deleted = FALSE")
@Getter
public class Member extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    @Column(name = "login_id")
    private String loginId;


    @Column(name = "password")
    private String password;


    @Column(name = "nickname")
    private String nickname;


    @Column(name = "profile_image_url")
    private String profileImageUrl;


    @Column(name = "introduction")
    private String introduction = "";


    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;


    public Member() {
    }


    @Builder
    public Member(String loginId, String password, String nickname, String profileImageUrl, String introduction) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction != null ? introduction : "";
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean hasId(Long id) {
        return this.id.equals(id);
    }
}



