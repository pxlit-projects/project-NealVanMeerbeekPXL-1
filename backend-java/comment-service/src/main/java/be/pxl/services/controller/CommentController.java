package be.pxl.services.controller;

import be.pxl.services.controller.dto.CommentDTO;
import be.pxl.services.controller.request.CreateCommentRequest;
import be.pxl.services.controller.request.UpdateCommentRequest;
import be.pxl.services.services.ICommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final ICommentService commentService;

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable String id, @RequestBody UpdateCommentRequest updateCommentRequest) {
        log.info("PUT /comment/{}", id);
        log.debug("Request Body: {}", updateCommentRequest);
        return new ResponseEntity<>(commentService.updateComment(id, updateCommentRequest), HttpStatus.OK);
    }
}
