package com.genre_service.genreservice.controller;

import com.genre_service.genreservice.model.Genre;
import com.genre_service.genreservice.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAll () {
        return genreService.getAll();
    }

    @PostMapping
    public String save (@RequestBody Genre genre) {
        return genreService.save(genre);
    }
}
