package com.post_service.postservice.service;

import com.post_service.postservice.exception.CustomException;
import com.post_service.postservice.model.Post;
import com.post_service.postservice.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Test
    void getAllPosts() {
        postService.getAllPosts();
        verify(postRepository).findAll();
    }

    @Test
    void createPost() {
        Post post = new Post(
                "test",
                "test test test"
        );

        postService.createPost(post);
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postRepository).save(postArgumentCaptor.capture());

        Post captorPost = postArgumentCaptor.getValue();
        assertThat(captorPost).isEqualTo(post);
    }

    @Test
    void willThrowWhenTitleIsTaken () {
        Post post = new Post(
                "test",
                "test test test"
        );

        given(postRepository.findByTitle(anyString()))
                .willReturn(new ArrayList<Post>(Collections.singleton(post)));

        assertThatThrownBy(() -> postService.createPost(post))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Post with this title is already exists");

        verify(postRepository, never()).save(any());
    }

    @Test
    void getPostsWithGenres() {
        assertThat(postService.getPostsWithGenres()).isInstanceOf(HashMap.class);
    }
}