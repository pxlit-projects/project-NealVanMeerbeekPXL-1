package be.pxl.services.client;

import be.pxl.services.controller.request.ReviewPostRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "post-service")
public interface PostClient {
    @PatchMapping("/post/post/{id}/reviewStatus")
    void sendNotification(@PathVariable String id, @Valid @RequestBody ReviewPostRequest reviewPostRequest);
}

