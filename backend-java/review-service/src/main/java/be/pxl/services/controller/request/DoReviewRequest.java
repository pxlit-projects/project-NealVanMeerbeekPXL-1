package be.pxl.services.controller.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DoReviewRequest {
    boolean approved;

    @Size(min = 1, message = "A review must have at least one comment")
    List<CommentRequest> comments;
}
