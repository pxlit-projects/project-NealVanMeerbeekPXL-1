package be.pxl.services.controller.dto;

import be.pxl.services.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class CommentDTO {
    UUID id;
    String author;
    LocalDateTime creationDate;
    UUID postId;
    String content;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.creationDate = comment.getCreationDate();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
    }
}
