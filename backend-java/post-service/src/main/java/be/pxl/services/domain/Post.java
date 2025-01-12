package be.pxl.services.domain;

import be.pxl.services.exception.DomainException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.PackagePrivate;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
@Data
@SuperBuilder
@NoArgsConstructor
public class Post {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();

    private String title;

    private String author;

    @Setter(AccessLevel.NONE)
    private boolean published;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    @Builder.Default
    private ReviewStatus reviewStatus = ReviewStatus.PENDING;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    public void publish() {
        if (reviewStatus != ReviewStatus.APPROVED) {
            throw new DomainException("Post", "published", true, String.format("post review status is '%s'", reviewStatus));
        }
        published = true;
    }

}
