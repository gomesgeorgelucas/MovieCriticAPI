package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.repository.ReactionRepository;
import org.george.moviecriticapi.service.interfaces.ReactionService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ReactionRepository reactionRepository;
}
