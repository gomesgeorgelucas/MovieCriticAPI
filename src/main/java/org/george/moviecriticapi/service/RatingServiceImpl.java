package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
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
    public RatingModel createRating(String token, Long movieId, Double movieRating) {
        UserModel user = userService.readUserByToken(token);
        MovieModel movie = movieService.readMovieById(movieId);

        for (RatingModel rating : movie.getMovieRatings()) {
            if (Objects.equals(rating.getRatingUser().getUserId(), user.getUserId())) {
                rating.setRatingScore(movieRating);
                RatingModel newRating = ratingRepository.save(rating); //DEBUG point
                return newRating;
            }
        }

        userService.setUserScore(user);
        RatingModel newRating = RatingModel.builder()
                .ratingMovie(movie)
                .ratingUser(user)
                .ratingScore(movieRating)
                .build();
        return ratingRepository.save(newRating);
    }

}
