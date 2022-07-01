package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.domain.dto.RatingDTO;
import org.george.moviecriticapi.domain.model.MovieModel;
import org.george.moviecriticapi.domain.model.RatingModel;
import org.george.moviecriticapi.domain.model.UserModel;
import org.george.moviecriticapi.repository.RatingRepository;
import org.george.moviecriticapi.service.interfaces.RatingService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final UserServiceImpl userService;
    private final MovieServiceImpl movieService;
    private final RatingRepository ratingRepository;

    @Override
    public RatingModel createRating(RatingDTO ratingDTO) {
        UserModel user = userService.readUserById(ratingDTO.getUserId());
        MovieModel movie = movieService.readMovieById(ratingDTO.getMovieId());

        for (RatingModel rating : movie.getMovieRatings()) {
            if (Objects.equals(rating.getRatingUser().getUserId(), user.getUserId())) {
                rating.setRatingScore(ratingDTO.getMovieRating());
                return ratingRepository.save(rating);
            }
        }

        userService.setUserScore(user);

        return ratingRepository.save(RatingModel.builder()
                .ratingMovie(movie)
                .ratingUser(user)
                .ratingScore(ratingDTO.getMovieRating())
                .build());
    }

}
