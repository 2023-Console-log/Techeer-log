CREATE DATABASE IF NOT EXISTS console_log;

use console_log;

drop table if exists refresh_token, member, project, comment, post_like, comment_likes;

CREATE TABLE comment
(
    comment_id   BIGINT AUTO_INCREMENT NOT NULL,
    parent_id    BIGINT NULL,
    member_id    BIGINT NULL,
    post_id      BIGINT NULL,
    soft_removed BIT(1)       NOT NULL,
    like_count   INT          NOT NULL,
    created_at   datetime NULL,
    message      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (comment_id)
);

CREATE TABLE comment_likes
(
    comment_likes_id BIGINT AUTO_INCREMENT NOT NULL,
    comment_id       BIGINT NULL,
    member_id        BIGINT NULL,
    CONSTRAINT pk_comment_likes PRIMARY KEY (comment_likes_id)
);

CREATE TABLE member
(
    member_id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime NOT NULL,
    updated_at        datetime NULL,
    profile_image_url VARCHAR(255) NULL,
    role_type         VARCHAR(255) NULL,
    login_id          VARCHAR(255) NULL,
    password          VARCHAR(255) NULL,
    nickname          VARCHAR(255) NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);

CREATE TABLE post_like
(
    post_like_id BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime NOT NULL,
    updated_at   datetime NULL,
    post_id      BIGINT NULL,
    member_id    BIGINT NULL,
    CONSTRAINT pk_postlike PRIMARY KEY (post_like_id)
);

CREATE TABLE project
(
    post_id        BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime     NOT NULL,
    updated_at     datetime NULL,
    main_image_url VARCHAR(255) NULL,
    title          VARCHAR(255) NOT NULL,
    subtitle       VARCHAR(255) NOT NULL,
    content        LONGTEXT     NOT NULL,
    view_count     INT          NOT NULL,
    like_count     INT          NOT NULL,
    start_date     date NULL,
    end_date       date NULL,
    github_link    VARCHAR(255) NULL,
    blog_link      VARCHAR(255) NULL,
    website_link   VARCHAR(255) NULL,
    member_id      BIGINT       NOT NULL,
    deleted        BIT(1) NULL,
    CONSTRAINT pk_project PRIMARY KEY (post_id)
);

CREATE TABLE refresh_token
(
    refresh_token_id BIGINT AUTO_INCREMENT NOT NULL,
    member_id        BIGINT NULL,
    token            VARCHAR(255) NULL,
    CONSTRAINT pk_refreshtoken PRIMARY KEY (refresh_token_id)
);

ALTER TABLE member
    ADD CONSTRAINT uc_member_nickname UNIQUE (nickname);

ALTER TABLE comment_likes
    ADD CONSTRAINT FK_COMMENT_LIKES_ON_COMMENT FOREIGN KEY (comment_id) REFERENCES comment (comment_id);

ALTER TABLE comment_likes
    ADD CONSTRAINT FK_COMMENT_LIKES_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_PARENT FOREIGN KEY (parent_id) REFERENCES comment (comment_id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_POST FOREIGN KEY (post_id) REFERENCES project (post_id);

ALTER TABLE post_like
    ADD CONSTRAINT FK_POSTLIKE_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE post_like
    ADD CONSTRAINT FK_POSTLIKE_ON_POST FOREIGN KEY (post_id) REFERENCES project (post_id);

ALTER TABLE project
    ADD CONSTRAINT FK_PROJECT_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);