package be.pxl.services.controller.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewReviewRequest {
    NewPostRequest newPostRequest;
}
