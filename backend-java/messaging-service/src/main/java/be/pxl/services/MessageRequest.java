package be.pxl.services;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest implements Serializable {
    @NotBlank(message = "Sender must not be blank")
    String sender;

    @NotBlank(message = "Recipient must not be blank")
    String recipient;

    @NotBlank(message = "Subject must not be blank")
    String subject;

    @NotBlank(message = "Message must not be blank")
    String message;

}
