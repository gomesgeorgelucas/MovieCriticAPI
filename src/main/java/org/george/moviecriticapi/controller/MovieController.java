package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.MovieServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mcapi/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieServiceImpl movieService;
}
