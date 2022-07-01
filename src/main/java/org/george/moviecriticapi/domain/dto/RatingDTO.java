package org.george.moviecriticapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private Long userId;
    private Long movieId;
    private Double movieRating;
}
