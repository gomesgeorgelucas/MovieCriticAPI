package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private MovieModel ratingMovie;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel ratingUser;

    @Column(name = "score_rating")
    private Double ratingScore;

    @Column(name = "date_created_rating")
    private Instant ratingCreated;
    @Column(name = "date_updated_rating")
    private Instant ratingUpdated;

    @PrePersist
    protected void onCreate() {
        this.ratingCreated = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.ratingUpdated = Instant.now();
    }
}
