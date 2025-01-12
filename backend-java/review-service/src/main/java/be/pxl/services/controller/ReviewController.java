package be.pxl.services.controller;

import be.pxl.services.client.PostClient;
import be.pxl.services.controller.dto.ReviewDTO;
import be.pxl.services.controller.request.DoReviewRequest;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.controller.request.ReviewPostRequest;
import be.pxl.services.domain.Review;
import be.pxl.services.security.AdminOnly;
import be.pxl.services.services.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final IReviewService reviewService;
    private final PostClient postClient;

    @AdminOnly
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll() {
        log.info("GET /review");
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @AdminOnly
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable String id) {
        log.info("GET /review/{}", id);
        return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewReviewRequest newReviewRequest) {
        log.info("POST /review");
        log.debug("Request Body: {}", newReviewRequest);
        reviewService.addReview(newReviewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @AdminOnly
    @PutMapping("/{id}")
    public ResponseEntity<Void> doReview(@PathVariable String id, @Valid @RequestBody DoReviewRequest reviewRequest) {
        log.info("PUT /review/{}", id);
        log.debug("Request Body: {}", reviewRequest);
        Review review = reviewService.doReview(id, reviewRequest);
        postClient.sendNotification(review.getPost().getId().toString(), ReviewPostRequest.builder().reviewStatus(review.getStatus()).build());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
