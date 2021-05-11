package com.post_service.postservice.service;

import com.post_service.postservice.client.GenreClient;
import com.post_service.postservice.exception.CustomException;
import com.post_service.postservice.model.Genre;
import com.post_service.postservice.model.Post;
import com.post_service.postservice.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final GenreClient genreClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final StreamBridge streamBridge;

    @Autowired
    public PostService(
            PostRepository postRepository,
            GenreClient genreClient,
            Resilience4JCircuitBreakerFactory circuitBreakerFactory,
            StreamBridge streamBridge
    ) {
        this.postRepository = postRepository;
        this.genreClient = genreClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.streamBridge = streamBridge;
    }

    public List<Post> getAllPosts () {
        return postRepository.findAll();
    }

    public String createPost (Post post) {
        var posts = postRepository.findByTitle(post.getTitle());

        if (posts.size() > 0) {
            throw new CustomException("Post with this title is already exists");
        }
        postRepository.save(post);

        return post.getId();
    }

    public HashMap<Object, Object> getPostsWithGenres() {
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("post");
        List<Genre> genres = circuitBreaker.run(genreClient::getAllGenres);

        var res = new HashMap<>();
        res.put("genres", genres);
        res.put("posts", postRepository.findAll());

        streamBridge.send("notificationEventSupplier-out-0", "returned all genres");

        return res;
    }
}
