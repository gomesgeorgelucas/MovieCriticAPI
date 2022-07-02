package org.george.moviecriticapi.service.interfaces;

import org.george.moviecriticapi.domain.model.RatingModel;

public interface RatingService {
    RatingModel createRating(String token, Long movieId, Double movieRating);
}
