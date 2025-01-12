package be.pxl.services.controller.dto;

import be.pxl.services.domain.Review;
import be.pxl.services.domain.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder
@AllArgsConstructor
public class ReviewDTO {
    UUID id;
    String reviewer;
    String status;
    LocalDateTime reviewDate;
    PostDTO post;
    Set<CommentDTO> comments;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.reviewer = review.getReviewer();
        this.status = review.getStatus().toString();
        this.reviewDate = review.getReviewDate();
        this.post = new PostDTO(review.getPost());
        this.comments = review.getComments().stream().map(CommentDTO::new).collect(Collectors.toSet());
    }
}
