package be.pxl.services.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DoReviewRequest {
    @NotNull(message = "Approved cannot be null")
    boolean approved;

    @NotBlank(message = "Reviewer cannot be blank")
    String reviewer;

    @NotNull(message = "Comments cannot be null")
    @Size(min = 1, message = "There must be at least one comment")
    List<CommentRequest> comments;
}
