package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mc_comment")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long commentId;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "mc_movie_comment",
            joinColumns = @JoinColumn(name="id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_movie")
    )
    private MovieModel commentMovie;

    @ManyToOne
    @JoinTable(
            name = "mc_user_comment",
            joinColumns = @JoinColumn(name="id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private UserModel commentUser;

    @Column(name = "message_comment")
    private String commentMessage;

    @Column(name = "is_comment_repeated")
    private Boolean isCommentRepeated;

    @OneToMany
    @JoinTable(
            name = "mc_comment_to_comment_response",
            joinColumns = @JoinColumn(name="id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_comment_response")
    )
    private Collection<CommentResponseModel> commentResponses;

    @OneToMany
    @JoinTable(
            name = "mc_comment_reaction",
            joinColumns = @JoinColumn(name="id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_reaction")
    )
    private Collection<ReactionModel> commentReactions;

    @Column(name = "date_created_comment")
    private Instant commentCreated;
    @Column(name = "date_updated_comment")
    private Instant commentUpdated;

    @PrePersist
    protected void onCreate() {
        this.commentCreated = Instant.now();

        this.commentResponses = new ArrayList<>();
        this.commentReactions = new ArrayList<>();
    }

    @PreUpdate
    protected void onUpdate() {
        this.commentUpdated = Instant.now();
    }
}
