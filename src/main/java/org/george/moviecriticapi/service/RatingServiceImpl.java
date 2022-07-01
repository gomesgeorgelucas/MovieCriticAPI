package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.repository.RatingRepository;
import org.george.moviecriticapi.service.interfaces.RatingService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
}
