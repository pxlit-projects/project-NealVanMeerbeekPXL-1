package be.pxl.services.client;

import be.pxl.services.controller.request.NewReviewRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "review-service")
public interface ReviewClient {
    @PostMapping("/review/review")
    ResponseEntity<Void> submitPostForReview(@Valid @RequestBody NewReviewRequest updatePostRequest);
}

