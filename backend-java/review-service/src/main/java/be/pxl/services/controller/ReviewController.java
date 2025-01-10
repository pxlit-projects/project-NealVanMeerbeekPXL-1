package be.pxl.services.controller;

import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.services.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final IReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewReviewRequest newReviewRequest) {
        log.info("POST /review");
        log.debug("Request Body: {}", newReviewRequest);
        reviewService.addReview(newReviewRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
