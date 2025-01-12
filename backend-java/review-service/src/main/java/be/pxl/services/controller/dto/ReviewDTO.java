package be.pxl.services.controller.dto;

import be.pxl.services.domain.Comment;
import be.pxl.services.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class ReviewDTO {
    UUID id;
    LocalDateTime reviewDate;
    PostDTO postDTO;
    Set<Comment> comments;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.reviewDate = review.getReviewDate();
        this.postDTO = new PostDTO(review.getPost());
        this.comments = review.getComments();
    }
}
