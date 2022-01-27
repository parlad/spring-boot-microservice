package com.movie.catalog.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.movie.catalog.models.CatalogItem;
import com.movie.catalog.models.Movie;
import com.movie.catalog.models.Rating;
import com.movie.catalog.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://MOVIE-RATING-SERVICE/ratingdata/users/" + userId,
				UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {

			Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICES/movies/" + rating.getMovieId(), Movie.class);
			// Movie movie = webClientBuilder
			// .build()
			// .get()
			// .uri("http://localhost:8082/movies/" + rating.getMovieId())
			// .retrieve().bodyToMono(Movie.class).block();

			return new CatalogItem(movie.getName(), "Iron man saves the earth again", rating.getRating());
		}).collect(Collectors.toList());

	}

}
