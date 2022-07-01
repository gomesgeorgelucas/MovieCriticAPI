package org.george.moviecriticapi.repository;

import org.george.moviecriticapi.domain.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Long> {
    Collection<MovieModel> findMovieModelsByMovieTitleContainingIgnoreCase(String movieName);
}
