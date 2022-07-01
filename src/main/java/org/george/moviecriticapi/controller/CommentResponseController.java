package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.service.CommentResponseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mcapi/comment-response")
@AllArgsConstructor
public class CommentResponseController {
    private final CommentResponseServiceImpl commentResponseService;
}
