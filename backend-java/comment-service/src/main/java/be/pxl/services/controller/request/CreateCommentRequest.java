package be.pxl.services.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCommentRequest {
    @NotNull(message = "postId must not be null")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "postId must be a valid UUIDv4")
    String postId;

    @NotBlank(message = "author must not be blank")
    String author;

    @NotBlank(message = "content must not be blank")
    String content;
}
