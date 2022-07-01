package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.repository.CommentResponseRepository;
import org.george.moviecriticapi.service.interfaces.CommentResponseService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentResponseImpl implements CommentResponseService {
    private final CommentResponseRepository commentResponseRepository;
}
