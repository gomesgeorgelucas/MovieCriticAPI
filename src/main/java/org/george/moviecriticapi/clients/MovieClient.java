package org.george.moviecriticapi.clients;

import org.george.moviecriticapi.domain.model.MovieModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://www.omdbapi.com", name= "movies")
public interface MovieClient {
    @GetMapping
    MovieModel readMovieByTitle(@RequestParam(value = "t") String title, @RequestParam(value = "apikey") String apikey);
}
