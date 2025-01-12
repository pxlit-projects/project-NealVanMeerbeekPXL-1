package be.pxl.services.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "post")
@Value
@SuperBuilder
@NoArgsConstructor(force = true)
public class Post {
    @Id
    @Setter(AccessLevel.NONE)
    UUID id;
}
