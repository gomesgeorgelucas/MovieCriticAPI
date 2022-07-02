package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.RatingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/mcapi/rating")
@AllArgsConstructor
public class RatingController {
    private final RatingServiceImpl ratingService;

    @GetMapping
    public ResponseEntity<?> createRating(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "movieId", defaultValue = "") Long movieId,
            @RequestParam(value = "movieRating", defaultValue = "") Double movieRating
            ) {
        return ResponseEntity.ok().body(ratingService.createRating(token, movieId, movieRating));
    }
}
