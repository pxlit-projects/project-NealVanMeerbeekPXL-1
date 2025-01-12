package be.pxl.services.services;

import be.pxl.services.controller.dto.ReviewDTO;
import be.pxl.services.controller.request.DoReviewRequest;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.domain.Comment;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.Review;
import be.pxl.services.exception.PendingReviewException;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewDTO::new).toList();
    }

    @Override
    public ReviewDTO getReviewById(String id) {
        return reviewRepository.findById(UUID.fromString(id)).map(ReviewDTO::new).orElseThrow(() -> new ResourceNotFoundException("Review", "ID", id));
    }

    @Override
    public void addReview(NewReviewRequest newReviewRequest) {
        reviewRepository.findByReviewDateIsNullAndPostId(UUID.fromString(newReviewRequest.getId())).ifPresent(review -> {
            throw new PendingReviewException();
        });

        Post post = Post.builder()
                .id(UUID.fromString(newReviewRequest.getId()))
                .creationDate(newReviewRequest.getCreationDate())
                .title(newReviewRequest.getTitle())
                .author(newReviewRequest.getAuthor())
                .content(newReviewRequest.getContent())
                .build();

        reviewRepository.save(Review.builder()
                .id(UUID.randomUUID())
                .post(post)
                .build());
    }

    @Override
    public Review doReview(String id, DoReviewRequest reviewRequest) {
        return reviewRepository.findById(UUID.fromString(id)).map(review -> {
            review.doReview(reviewRequest.getReviewer(), reviewRequest.isApproved(), reviewRequest.getComments().stream().map(commentRequest -> Comment.builder().review(review).content(commentRequest.content()).build()).collect(Collectors.toSet()));
            return reviewRepository.save(review);
        }).orElseThrow(() -> new ResourceNotFoundException("Review", "ID", id));
    }

}
