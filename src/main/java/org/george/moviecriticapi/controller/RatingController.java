package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.domain.dto.RatingDTO;
import org.george.moviecriticapi.service.RatingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/mcapi/rating")
@AllArgsConstructor
public class RatingController {
    private final RatingServiceImpl ratingService;

    @PostMapping
    public ResponseEntity<?> createRating(
            @RequestBody
            @Valid
            RatingDTO ratingDTO) {
        return ResponseEntity.ok().body(ratingService.createRating(ratingDTO));
    }
}
