package com.likelion.springsession.post.controller;

import com.likelion.springsession.post.entity.Post;
import com.likelion.springsession.post.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }
}


