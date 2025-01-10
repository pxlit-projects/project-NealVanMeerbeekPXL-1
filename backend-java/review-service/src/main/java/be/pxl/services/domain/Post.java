package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    private LocalDateTime creationDate;

    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

}
