package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mc_movie")
public class MovieModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_movie")
    private Long movieId;

    @Column(name = "title_movie")
    @JsonProperty(value = "Title")
    private String movieTitle;
    @Column(name = "year_movie")
    @JsonProperty(value = "Year")
    private String movieYear;
    @Column(name = "genre_movie")
    @JsonProperty(value = "Genre")
    private String movieGenre;
    @Column(name = "director_movie")
    @JsonProperty(value = "Director")
    private String movieDirector;
    @Column(name = "writer_movie")
    @JsonProperty(value = "Writer")
    private String movieWriter;
    @Column(name = "country_movie")
    @JsonProperty(value = "Country")
    private String movieCountry;

    @OneToMany
    @JoinTable(
            name = "mc_movie_comment",
            joinColumns = @JoinColumn(name="id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_comment")
    )
    private Collection<CommentModel> movieComments;

    @OneToMany
    @JoinTable(
            name = "mc_movie_rating",
            joinColumns = @JoinColumn(name="id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_rating")
    )
    private Collection<RatingModel> movieRatings;

    @Column(name = "average_rating_movie")
    private Double movieAverageRating;

    @Column(name = "date_created_comment")
    private Instant movieCreated;
    @Column(name = "date_updated_comment")
    private Instant movieUpdated;

    @PrePersist
    protected void onCreate() {
        this.movieCreated = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.movieUpdated = Instant.now();
    }
}
