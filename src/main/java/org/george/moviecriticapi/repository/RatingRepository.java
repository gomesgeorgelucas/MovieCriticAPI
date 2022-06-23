package org.george.moviecriticapi.repository;

import org.george.moviecriticapi.domain.model.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Long> {
}
