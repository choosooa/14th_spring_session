package com.likelion.springsession.comment.repository;

import com.likelion.springsession.comment.entity.Comment;
import com.likelion.springsession.post.entity.Post;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndPostId(Long id, Long postId);

    Comment[] findAllByPost(Post post);
}
