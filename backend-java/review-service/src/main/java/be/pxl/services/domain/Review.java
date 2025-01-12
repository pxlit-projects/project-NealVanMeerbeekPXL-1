package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDateTime reviewDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private Post post;

    @Singular
    @OneToMany(mappedBy = "review", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @Transactional
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setReview(this);
    }
}
