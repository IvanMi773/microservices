package com.post_service.postservice.repository;

import com.post_service.postservice.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void getTitleById () {
        Post post = postRepository.save(new Post(
                "test",
                "test test test",
                new Date()
        ));

        assertThat(postRepository.findByTitle(post.getTitle())).isNotNull();
        postRepository.deleteById(post.getId());
    }
}