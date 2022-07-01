package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.RatingServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mcapi/rating")
@AllArgsConstructor
public class RatingController {
    private final RatingServiceImpl ratingService;
}
