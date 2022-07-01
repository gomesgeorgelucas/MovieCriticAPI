package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.george.moviecriticapi.domain.enums.UserRoleEnum;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "mc_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;
    @Column(name = "name_user", nullable = false)
    @NotBlank
    private String userName;
    @Email
    @Column(name = "email_user", nullable = false, unique = true)
    @NotBlank
    private String userEmail;
    @Column(name = "password_user", nullable = false)
    @NotBlank
    private String userPassword;
    @Column(name = "score_user")
    @Min(0)
    private Long userScore;

    @Column(name = "role_user")
    private UserRoleEnum userRole;

    @JsonIgnore
    @OneToMany(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    private Collection<RatingModel> userRatings;

    @JsonIgnore
    @OneToMany
    private Collection<CommentModel> userComments;

    @JsonIgnore
    @OneToMany
    private Collection<CommentResponseModel> userCommentResponses;

    @JsonIgnore
    @OneToMany
    private Collection<ReactionModel> userReactions;

    @Column(name = "date_created_user", updatable = false)
    private Instant userCreated;
    @Column(name = "date_updated_user")
    private Instant userUpdated;

    @PrePersist
    protected void onCreate() {
        this.userCreated = Instant.now();

        this.userScore = 0L;
        this.userRole = UserRoleEnum.READER;

        this.userComments = new ArrayList<>();
        this.userRatings = new ArrayList<>();
        this.userReactions = new ArrayList<>();
    }

    @PreUpdate
    protected void onUpdate() {
        this.userUpdated = Instant.now();
    }
}
