package be.pxl.services.rabbitmq;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class PostPublishedMessage implements Serializable {
    String postId;
}
