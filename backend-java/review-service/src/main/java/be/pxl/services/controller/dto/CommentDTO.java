package be.pxl.services.controller.dto;

import be.pxl.services.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CommentDTO {
    Long id;
    String content;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
