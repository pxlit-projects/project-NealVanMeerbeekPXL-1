package be.pxl.services.controller.dto;

import be.pxl.services.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class PublicPostDTO {
    UUID id;
    LocalDateTime creationDate;
    String title;
    String author;
    String content;

    public PublicPostDTO(Post post) {
        this.id = post.getId();
        this.creationDate = post.getCreationDate();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
    }

}
