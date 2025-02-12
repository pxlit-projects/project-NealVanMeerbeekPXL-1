package be.pxl.services.controller;

import be.pxl.services.controller.dto.CommentDTO;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.CreateCommentRequest;
import be.pxl.services.services.ICommentService;
import be.pxl.services.services.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final IPostService postService;
    private final ICommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> findById(@PathVariable String postId) {
        log.info("GET /post/{}", postId);
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> create(@PathVariable String postId, @RequestBody CreateCommentRequest createCommentRequest) {
        log.info("POST /post/{}", postId);
        log.debug("Request Body: {}", createCommentRequest);
        return new ResponseEntity<>(commentService.addComment(postId, createCommentRequest), HttpStatus.CREATED);
    }
}
