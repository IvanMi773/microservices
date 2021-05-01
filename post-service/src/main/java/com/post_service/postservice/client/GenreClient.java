package com.post_service.postservice.client;

import com.post_service.postservice.model.Genre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "genre-service")
public interface GenreClient {

    @GetMapping("/api/v1/genre")
    List<Genre> getAllGenres ();
}
