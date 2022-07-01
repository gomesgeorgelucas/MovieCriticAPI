package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.ReactionServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mcapi/reaction")
@AllArgsConstructor
public class ReactionController {
    private final ReactionServiceImpl reactionService;
}
