package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.MovieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mcapi/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping
    public ResponseEntity<?> readAllMovies() {
        return ResponseEntity.ok().body(movieService.readAllMovies());
    }

    @GetMapping("/by-title")
    public ResponseEntity<?> readMovieByMovieTitle(@RequestParam(value = "movieTitle") String movieTitle) {
        return ResponseEntity.ok().body(movieService.readMovieByMovieTitle(movieTitle));
    }
}
