package org.george.moviecriticapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
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

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieModel commentMovie;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel commentUser;

    @Column(name = "message_comment")
    private String commentMessage;

    @OneToMany(mappedBy = "mc_comment")
    private Collection<CommentResponseModel> commentResponses;

    @Column(name = "date_created_comment")
    private ZonedDateTime commentCreated;
    @Column(name = "date_updated_comment")
    private ZonedDateTime commentUpdated;

    @PrePersist
    protected void onCreate() {
        this.commentCreated = ZonedDateTime.now();

        this.commentResponses = new ArrayList<>();
    }

    @PreUpdate
    protected void onUpdate() {
        this.commentUpdated = ZonedDateTime.now();
    }
}
