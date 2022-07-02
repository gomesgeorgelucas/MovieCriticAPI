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
    @JoinColumn(name = "id_movie")
    private MovieModel commentMovie;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel commentUser;

    @Column(name = "message_comment")
    private String commentMessage;

    @Column(name = "is_comment_repeated")
    private Boolean isCommentRepeated;

    @OneToMany(mappedBy = "", targetEntity = CommentModel.class)
    private Collection<CommentResponseModel> commentResponses;

    @OneToMany(mappedBy = "", targetEntity = ReactionModel.class)
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
