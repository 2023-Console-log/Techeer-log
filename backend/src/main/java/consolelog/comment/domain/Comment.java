package consolelog.comment.domain;


import jakarta.persistence.*;
import lombok.Builder;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    //대댓글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();

    private String nickname;


    @Embedded
    private Message message;

    private boolean softRemoved;

    //댓글 좋아요 수
    private int likeCount;

    @CreatedDate
    private LocalDateTime created_at;


    @Builder
    public Comment(Member member, Post post, String nickname, String message, Comment parent) {
        this.member = member;
        this.post = post;
        this.nickname = nickname;
        this.message = new Message(message);
        this.parent = parent;
    }

    public static Comment parent(Member member, Post post, String nickname, String message) {
        return new Comment(member, post, nickname, message, null);
    }

    public static Comment child(Member member, Post post, String nickname, String message, Comment parent) {
        Comment child = new Comment(member, post, nickname, message, parent);
        parent.getChildren().add(child); // 자식 댓글 추가, 자식 댓글 목록 반환, 새로운 자식 댓글 추가
        return child;
    }

    //    댓글 작성자의 아이디와 일치하는지 확인
    public void validateOwner(Long accessMemberId) {
        if (!accessMemberId.equals(member.getId())) {
            throw new AuthorizationException();
        }
    }

    public boolean isPostWriter() {
        return post.getMember().equls(member);
    }

    // 댓글 수정, 삭제 할 때 해당 댓글 작성한 사용자인지 확인
    public boolean isAuthrized(Long acessMemberId) {
        if (accessMemberId == null) {
            return false;
        }
        return member.getId().equls(accessMemberId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getParent() {
        return parent;
    }


    public List<Comment> getChildren() {
        return children;
    }


    public Member getMember() {
        return member;
    }


    public Post getPost() {
        return post;
    }


    public String getNickname() {
        if (isBlocked()) {
            return BLIND_COMMENT_MESSAGE;
        }
        return nickname;
    }

    public Message getMessage() {
        return message;
    }


    public boolean isSoftRemoved() {
        return softRemoved;
    }

    public void setSoftRemoved(boolean softRemoved) {
        this.softRemoved = softRemoved;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
//뭔 소린지 일도 모르겠습니다
}

