package org.george.moviecriticapi.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.repository.MovieRepository;
import org.george.moviecriticapi.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
}
