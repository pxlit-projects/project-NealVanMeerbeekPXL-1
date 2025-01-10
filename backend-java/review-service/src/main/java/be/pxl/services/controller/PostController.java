package be.pxl.services.controller;

import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.services.PostService;
import jakarta.validation.Valid;
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
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody NewPostRequest newPostRequest) {
        log.info("POST /post");
        log.debug("Request Body: {}", newPostRequest);
        postService.addPost(newPostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
