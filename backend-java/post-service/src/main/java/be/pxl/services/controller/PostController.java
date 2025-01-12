package be.pxl.services.controller;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.controller.request.PublishPostRequest;
import be.pxl.services.controller.request.ReviewPostRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import be.pxl.services.rabbitmq.PostPublishedMessage;
import be.pxl.services.security.AdminOnly;
import be.pxl.services.services.IPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final IPostService postService;

    @AdminOnly
    @GetMapping
    public List<PostDTO> findAll() {
        log.info("GET /post");
        return postService.getAllPosts();
    }

    @AdminOnly
    @GetMapping("/{id}")
    public PostDTO findById(@PathVariable String id) {
        log.info("GET /post/{id}");
        return postService.getPostById(id);
    }

    @AdminOnly
    @PostMapping
    public ResponseEntity<PostDTO> add(@Valid @RequestBody NewPostRequest newPostRequest) {
        log.info("POST /post");
        log.debug("Request Body: {}", newPostRequest);
        return new ResponseEntity<>(postService.addPost(newPostRequest), HttpStatus.CREATED);
    }

    @AdminOnly
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable String id, @Valid @RequestBody UpdatePostRequest updatePostRequest) {
        log.info("PUT /post/{}", id);
        log.debug("Request Body: {}", updatePostRequest);
        return new ResponseEntity<>(postService.updatePost(id, updatePostRequest), HttpStatus.OK);
    }

    @PatchMapping("/{id}/reviewStatus")
    public ResponseEntity<Void> updateReviewStatus(@PathVariable String id, @Valid @RequestBody ReviewPostRequest reviewPostRequest) {
        log.info("PATCH /post/{}/reviewStatus", id);
        log.debug("Request Body: {}", reviewPostRequest);
        postService.updatePostReviewStatus(id, reviewPostRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @AdminOnly
    @PatchMapping("/{id}/published")
    public ResponseEntity<Void> publish(@PathVariable String id, @Valid @RequestBody PublishPostRequest publishPostRequest) {
        log.info("PATCH /post/{} (publish)", id);
        log.debug("Request Body: {}", publishPostRequest);
        postService.publishPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
