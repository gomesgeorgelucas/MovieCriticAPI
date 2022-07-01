package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mcapi/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentService;
}
