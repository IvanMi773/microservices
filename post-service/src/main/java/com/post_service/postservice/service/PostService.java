package com.post_service.postservice.service;

import com.post_service.postservice.client.GenreClient;
import com.post_service.postservice.model.Genre;
import com.post_service.postservice.model.Post;
import com.post_service.postservice.repository.PostRepository;
import io.vavr.CheckedRunnable;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final GenreClient genreClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    public PostService(PostRepository postRepository, GenreClient genreClient, Resilience4JCircuitBreakerFactory circuitBreakerFactory) {
        this.postRepository = postRepository;
        this.genreClient = genreClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public List<Post> getAllPosts () {
        return postRepository.findAll();
    }

    public String createPost (Post post) {
        postRepository.save(post);

        return post.getId();
    }

    public HashMap<Object, Object> getPostsWithGenres() {
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("post");
        List<Genre> genres = circuitBreaker.run(genreClient::getAllGenres);

        var res = new HashMap<>();
        res.put("genres", genres);
        res.put("posts", postRepository.findAll());

        return res;
    }
}
