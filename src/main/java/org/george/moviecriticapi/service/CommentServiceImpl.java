package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.repository.CommentRepository;
import org.george.moviecriticapi.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
}
