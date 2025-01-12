package be.pxl.services.rabbitmq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
public record PostPublishedMessage(@JsonProperty("postId") @NotNull String postId) {
    @JsonCreator
    public PostPublishedMessage(@JsonProperty("postId") String postId) {
        this.postId = postId;
    }
}
