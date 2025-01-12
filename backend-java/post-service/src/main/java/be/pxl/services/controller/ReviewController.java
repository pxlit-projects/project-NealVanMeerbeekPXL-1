package be.pxl.services.controller;

import be.pxl.services.client.ReviewClient;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import be.pxl.services.security.AdminOnly;
import be.pxl.services.services.IPostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final IPostService postService;
    private final ReviewClient reviewClient;

    @AdminOnly
    @PostMapping("/{postId}")
    public ResponseEntity<Void> add(@PathVariable String postId, @Valid @RequestBody NewReviewRequest newReviewRequest) {
        log.info("POST /review");
        log.debug("Request Body: {}", newReviewRequest);
        postService.updatePost(postId, UpdatePostRequest.builder().title(newReviewRequest.getTitle()).content(newReviewRequest.getContent()).author(newReviewRequest.getAuthor()).build());
        return reviewClient.submitPostForReview(newReviewRequest);
    }

}
