package com.likelion.springsession.post.service;

import com.likelion.springsession.post.dto.PostCreateRequest;
import com.likelion.springsession.post.dto.PostDetailResponse;
import com.likelion.springsession.post.dto.PostSummaryResponse;
import com.likelion.springsession.post.dto.PostUpdateRequest;
import com.likelion.springsession.post.entity.Post;
import com.likelion.springsession.post.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostSummaryResponse> getPostSummaries() {
        List<Post> posts = postRepository.findAll();
        List<PostSummaryResponse> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(new PostSummaryResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getCreatedAt()
            ));
        }
        return responses;
    }

    public PostDetailResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return toDetailResponse(post);
    }

    public PostDetailResponse createPost(PostCreateRequest request) {
        Post post = new Post(request.getTitle(), request.getContent());
        Post savedPost = postRepository.save(post);
        return toDetailResponse(savedPost);
    }

    @Transactional
    public PostDetailResponse updatePost(Long postId, PostUpdateRequest request) {
        Post post = findPostById(postId);
        post.update(request.getTitle(), request.getContent());
        return toDetailResponse(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow();
    }

    private PostDetailResponse toDetailResponse(Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}