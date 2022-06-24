package org.george.moviecriticapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mc_comment_response")
public class CommentResponseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment_response")
    private Long commentResponseId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel commentResponseUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "mc_comment_to_comment_responses",
            joinColumns = @JoinColumn(name="id_comment_response"),
            inverseJoinColumns = @JoinColumn(name = "id_comment")
    )
    private CommentModel commentResponseComment;

    @Column(name = "message_comment_response")
    private String commentResponseMessage;

    @Column(name = "date_created_comment_response")
    private ZonedDateTime commentResponseCreated;
    @Column(name = "date_updated_comment_response")
    private ZonedDateTime commentResponseUpdated;

    @PrePersist
    protected void onCreate() {
        this.commentResponseCreated = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.commentResponseUpdated = ZonedDateTime.now();
    }
}
