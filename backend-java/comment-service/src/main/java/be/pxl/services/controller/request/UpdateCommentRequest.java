package be.pxl.services.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateCommentRequest(@JsonProperty("content") @NotNull String content) {
    @JsonCreator
    public UpdateCommentRequest(@JsonProperty("content") String content) {
        this.content = content;
    }
}
