package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mc_rating")
public class RatingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rating")
    private Long ratingId;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "mc_movie_rating",
            joinColumns = @JoinColumn(name="id_rating"),
            inverseJoinColumns = @JoinColumn(name = "id_movie")
    )
    private MovieModel ratingMovie;

    @ManyToOne
    @JoinTable(
            name = "mc_user_rating",
            joinColumns = @JoinColumn(name="id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private UserModel ratingUser;

    @Column(name = "score_rating")
    private Double ratingScore;

    @Column(name = "date_created_rating")
    private ZonedDateTime ratingCreated;
    @Column(name = "date_updated_rating")
    private ZonedDateTime ratingUpdated;

    @PrePersist
    protected void onCreate() {
        this.ratingCreated = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.ratingUpdated = ZonedDateTime.now();
    }
}
