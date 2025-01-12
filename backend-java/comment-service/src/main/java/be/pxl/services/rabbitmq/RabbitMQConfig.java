package be.pxl.services.rabbitmq;

import be.pxl.services.services.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConfig {
    private final IPostService postService;

    @RabbitListener(queues = "publishPostQueue")
    public void listen(PostPublishedMessage postPublishedMessage) {
        log.info("Message received on publishPostQueue");
        log.debug("Message read from publishPostQueue : {}", postPublishedMessage);
        postService.addPost(postPublishedMessage.postId());
    }
}
