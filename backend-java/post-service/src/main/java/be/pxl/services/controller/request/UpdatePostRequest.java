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
public class UpdatePostRequest {
    @NotBlank(message = "title must not be blank")
    String title;

    @NotBlank(message = "author must not be blank")
    String author;

    @NotBlank(message = "content must not be blank")
    String content;

}
