package com.likelion.springsession.comment.entity;

import com.likelion.springsession.post.entity.Post;
import com.likelion.springsession.user.entity.User;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(Post post, String content){
        this.post = post;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        post.getComments().add(this);
    }

    public void update(String content){
        this.content = content;
    }

    public void clearPost() {
        this.post = null;
    }

    @ManyToOne(fetch = FetchType.LAZY)              // ← 추가
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Comment(Post post, User author, String content) {   // ← 생성자 수정
        this.post = post;
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        post.getComments().add(this);      // 4주차 연관관계 편의 메서드 -그대로 유지
    }

    public boolean isAuthor(Long userId) {          // ← 추가 (과제에서 씁니다)
        return this.author.getId().equals(userId);
    }
}
