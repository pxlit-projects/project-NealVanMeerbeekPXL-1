package be.pxl.services.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class NewReviewRequest {
    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "id must be a valid UUIDv4")
    String id;

    @NotNull
    @Past(message = "creationDate must be a valid ISO-8601 date that lies in the past")
    LocalDateTime creationDate;

    @NotBlank(message = "title must not be blank")
    String title;

    @NotBlank(message = "author must not be blank")
    String author;

    @NotBlank(message = "content must not be blank")
    String content;
}
