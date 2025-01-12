package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
@Value
@SuperBuilder
@NoArgsConstructor(force = true)
public class Post {
    @Id
    UUID id;

    LocalDateTime creationDate;

    String title;

    String author;

    @Lob
    @Column(columnDefinition = "TEXT")
    String content;

}
