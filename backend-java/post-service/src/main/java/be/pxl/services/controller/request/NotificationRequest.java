package be.pxl.services.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NotificationRequest {
    @NotBlank(message = "Sender must not be blank")
    String sender;

    @NotBlank(message = "Recipient must not be blank")
    String recipient;

    @NotBlank(message = "Subject must not be blank")
    String subject;

    @NotBlank(message = "Message must not be blank")
    String message;

}
