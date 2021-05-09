package com.post_service.postservice;

import com.post_service.postservice.controller.PostController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PostServiceApplicationTests {

	@Autowired
	private PostController postController;

	@Test
	void contextLoads() {
		assertThat(postController).isNotNull();
	}

}
