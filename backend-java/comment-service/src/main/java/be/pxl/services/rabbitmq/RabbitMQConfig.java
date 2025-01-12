package be.pxl.services.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfig {
//    @RabbitListener(queues = "publishPostQueue")
//    public void listen(MessageRequest messageRequest) {
//        log.info("Message received on MyQueue");
//        log.debug("Message read from myQueue : {}", messageRequest);
//    }
}
