package be.pxl.services.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "post-service")
public interface PostClient {
//    @PostMapping("/api/post")
//    void sendNotification(@Valid @RequestBody NotificationRequest notificationRequest);

}

