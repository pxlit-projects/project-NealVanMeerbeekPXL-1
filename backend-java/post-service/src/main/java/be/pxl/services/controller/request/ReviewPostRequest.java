package be.pxl.services.controller.request;

import be.pxl.services.domain.ReviewStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReviewPostRequest(@JsonProperty("reviewStatus") @NotNull ReviewStatus reviewStatus) {
    @JsonCreator
    public ReviewPostRequest(@JsonProperty("reviewStatus") ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
