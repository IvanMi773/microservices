package com.genre_service.genreservice.service;

import com.genre_service.genreservice.model.Genre;
import com.genre_service.genreservice.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public String save (Genre genre) {
        System.out.println(genre.getName());
        genreRepository.insert(genre);
        return "";
    }

    public List<Genre> getAll () {
        return genreRepository.findAll();
    }
}
