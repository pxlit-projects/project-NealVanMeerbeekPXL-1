package be.pxl.services.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PublishPostRequest(@JsonProperty("reviewStatus") @NotNull boolean published) {
    @JsonCreator
    public PublishPostRequest(@JsonProperty("reviewStatus") boolean published) {
        this.published = published;
    }
}
