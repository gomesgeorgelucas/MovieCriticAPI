package org.george.moviecriticapi.repository;

import org.george.moviecriticapi.domain.model.ReactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionModel, Long> {
}
