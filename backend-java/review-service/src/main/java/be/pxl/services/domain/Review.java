package be.pxl.services.domain;

import be.pxl.services.exception.DomainException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
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
    private UUID id;

    private String reviewer;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    @Builder.Default
    private ReviewStatus status = ReviewStatus.PENDING;

    private LocalDateTime reviewDate;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private Post post;

    @Singular
    @OneToMany(mappedBy = "review", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @Transactional
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setReview(this);
    }

    @Transactional
    public void doReview(String reviewer, boolean approved, Set<Comment> comments) {
        if (reviewDate != null) {
            throw new DomainException("reviewDate, reviewer, status and comments", "the new values", "Review", "Review has already been done");
        } else if (!approved && comments.isEmpty()) {
            throw new DomainException("status", "Rejected", "Review", "Rejected review must have at least one comment");
        }
        this.reviewer = reviewer;
        this.status = approved ? ReviewStatus.APPROVED : ReviewStatus.REJECTED;
        this.reviewDate = LocalDateTime.now();
        comments.forEach(this::addComment);
    }
}
