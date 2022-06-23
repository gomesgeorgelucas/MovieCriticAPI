package org.george.moviecriticapi.repository;

import org.george.moviecriticapi.domain.model.CommentResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentResponseRepository extends JpaRepository<CommentResponseModel, Long> {
}
