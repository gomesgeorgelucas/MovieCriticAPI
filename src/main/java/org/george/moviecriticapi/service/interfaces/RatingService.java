package org.george.moviecriticapi.service.interfaces;

import org.george.moviecriticapi.domain.dto.RatingDTO;
import org.george.moviecriticapi.domain.model.RatingModel;

public interface RatingService {
    RatingModel createRating(RatingDTO ratingDTO);
}
