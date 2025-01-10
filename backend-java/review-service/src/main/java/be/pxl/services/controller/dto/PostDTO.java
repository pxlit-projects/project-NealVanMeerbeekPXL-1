package be.pxl.services.controller.dto;

import be.pxl.services.domain.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class PostDTO {
    UUID id;
    LocalDateTime creationDate;
    String title;
    String author;
    String reviewStatus;
    String content;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.creationDate = post.getCreationDate();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.reviewStatus = post.getReviewStatus().toString();
        this.content = post.getContent();
    }

}
