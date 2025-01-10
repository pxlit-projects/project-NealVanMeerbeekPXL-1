package be.pxl.services.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PublishPostRequest {
    @NotNull
    boolean published;
}
