package be.pxl.services.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CommentRequest(@JsonProperty("content") @NotBlank String content) {
    @JsonCreator
    public CommentRequest(@JsonProperty("content") String content) {
        this.content = content;
    }
}
