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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mc_reaction")
public class ReactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reaction")
    private Long reactionId;

    @ManyToOne
    @JoinTable(
            name = "mc_reaction_user",
            joinColumns = @JoinColumn(name="id_reaction"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private UserModel reactionUser;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "mc_comment_reaction",
            joinColumns = @JoinColumn(name="id_reaction"),
            inverseJoinColumns = @JoinColumn(name = "id_comment")
    )
    private CommentModel reactionComment;

    @Column(name = "status_reaction")
    private Boolean reactionStatus;

    @Column(name = "date_created_reaction")
    private ZonedDateTime reactionCreated;
    @Column(name = "date_updated_reaction")
    private ZonedDateTime reactionUpdated;

    @PrePersist
    protected void onCreate() {
        this.reactionCreated = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.reactionUpdated = ZonedDateTime.now();
    }
}
