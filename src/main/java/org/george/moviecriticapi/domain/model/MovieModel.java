package org.george.moviecriticapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    private String movieTitle;
    @Column(name = "year_movie")
    private String movieYear;
    @Column(name = "genre_movie")
    private String movieGenre;
    @Column(name = "director_movie")
    private String movieDirector;
    @Column(name = "writer_movie")
    private String movieWriter;
    @Column(name = "country_movie")
    private String movieCountry;

    @OneToMany(mappedBy = "mc_movie")
    private Collection<CommentModel> movieComments;

    @OneToMany(mappedBy = "mc_movie")
    private Collection<RatingModel> movieRatings;

    private Double movieAverageRating;

    @Column(name = "date_created_comment")
    private ZonedDateTime movieCreated;
    @Column(name = "date_updated_comment")
    private ZonedDateTime movieUpdated;

    @PrePersist
    protected void onCreate() {
        this.movieCreated = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.movieUpdated = ZonedDateTime.now();
    }
}
