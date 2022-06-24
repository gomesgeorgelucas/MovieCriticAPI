package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.george.moviecriticapi.domain.enums.UserRoleEnum;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    @Column(name = "name_user")
    private String userName;
    @Column(name = "email_user")
    private String userEmail;
    @Column(name = "password_user")
    private String userPassword;
    @Column(name = "score_user")
    private Long userScore;

    @Column(name = "role_user")
    private UserRoleEnum userRole;

    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "mc_user_rating",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rating")
    )
    private Collection<RatingModel> userRatings;

    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "mc_user_comment",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_comment")
    )
    private Collection<CommentModel> userComments;

    @Column(name = "date_created_user")
    private ZonedDateTime userCreated;
    @Column(name = "date_updated_user")
    private ZonedDateTime userUpdated;

    @PrePersist
    protected void onCreate() {
        this.userCreated = ZonedDateTime.now();

        this.userScore = 0L;
        this.userRole = UserRoleEnum.READER;

        this.userComments = new ArrayList<>();
        this.userRatings = new ArrayList<>();
    }

    @PreUpdate
    protected void onUpdate() {
        this.userUpdated = ZonedDateTime.now();
    }
}
