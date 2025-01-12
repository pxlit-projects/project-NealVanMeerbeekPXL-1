package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Data
@SuperBuilder
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;

    private LocalDateTime creationDate;

    @OneToOne(optional = false)
    private Post post;

    private String content;

}
