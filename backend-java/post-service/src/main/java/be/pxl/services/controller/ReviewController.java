package be.pxl.services.controller;

import be.pxl.services.client.ReviewClient;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import be.pxl.services.security.AdminOnly;
import be.pxl.services.services.IPostService;
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
    public ResponseEntity<Void> add(@PathVariable String postId, @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        log.info("POST /review");
        log.debug("Request Body: {}", updatePostRequest);
        PostDTO updatedPost = postService.updatePost(postId, updatePostRequest);
        return reviewClient.submitPostForReview(NewReviewRequest.builder().id(updatedPost.getId().toString()).creationDate(updatedPost.getCreationDate()).title(updatedPost.getTitle()).author(updatedPost.getAuthor()).content(updatedPost.getContent()).build());
    }

}
