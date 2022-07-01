package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.clients.MovieClient;
import org.george.moviecriticapi.config.EnvPropertiesConfig;
import org.george.moviecriticapi.domain.model.MovieModel;
import org.george.moviecriticapi.exception.InvalidRequestException;
import org.george.moviecriticapi.repository.MovieRepository;
import org.george.moviecriticapi.service.interfaces.MovieService;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final EnvPropertiesConfig properties;

    private final MovieRepository movieRepository;

    private final MovieClient movieClient;

    private final UserServiceImpl userService;

    @Override
    public MovieModel createMovie(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    @Override
    public Collection<MovieModel> readAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieModel readMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new InvalidRequestException(APIMessages.INVALID_REQUEST_MOVIE_NOT_FOUND_DSC));
    }

    @Override
    public Collection<MovieModel> readMovieByMovieTitle(String movieTitle) {
        Collection<MovieModel> storedMovies =
                movieRepository.findMovieModelsByMovieTitleContainingIgnoreCase(movieTitle);

        if (storedMovies.isEmpty()) {
            MovieModel omdbMovie = movieClient.readMovieByTitle(movieTitle, properties.getApiKey());

            if (omdbMovie.getMovieTitle() == null) {
                throw new InvalidRequestException(APIMessages.INVALID_REQUEST_MOVIE_TITLE_NOT_FOUND_DSC);
            }

            storedMovies.add(createMovie(
                    MovieModel.builder()
                            .movieTitle(omdbMovie.getMovieTitle())
                            .movieYear(omdbMovie.getMovieYear())
                            .movieGenre(omdbMovie.getMovieGenre())
                            .movieDirector(omdbMovie.getMovieDirector())
                            .movieWriter(omdbMovie.getMovieWriter())
                            .movieCountry(omdbMovie.getMovieCountry())
                            .build()
            ));
        }

        return storedMovies;
    }
}
