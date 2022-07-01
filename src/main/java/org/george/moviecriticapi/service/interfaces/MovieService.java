package org.george.moviecriticapi.service.interfaces;

import org.george.moviecriticapi.domain.model.MovieModel;

import java.util.Collection;

public interface MovieService {
    MovieModel createMovie(MovieModel movieModel);
    Collection<MovieModel> readAllMovies();
    MovieModel readMovieById(Long id);
    Collection<MovieModel> readMovieByMovieTitle(String movieName);
}
