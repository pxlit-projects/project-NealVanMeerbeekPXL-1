package be.pxl.services.controller.dto;

import be.pxl.services.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class PostDTO {
    UUID id;
    List<CommentDTO> comments;

    public PostDTO(Post post, List<CommentDTO> comments) {
        this.id = post.getId();
        this.comments = comments;
    }
}
