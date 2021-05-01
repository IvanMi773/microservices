package com.post_service.postservice.controller;

import com.post_service.postservice.model.Post;
import com.post_service.postservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts () {
        return postService.getAllPosts();
    }

    @PostMapping
    public String save (@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/genres")
    public HashMap<Object, Object> getPostsWithGenres () {
        return postService.getPostsWithGenres();
    }
}
