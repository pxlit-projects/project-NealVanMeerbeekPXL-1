package be.pxl.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * MessagingServiceApplication
 */
@SpringBootApplication
@Slf4j
public class MessagingServiceApplication
{
    public static void main( String[] args )
    {
        log.debug("Messaging Service microservice started");
        SpringApplication.run(MessagingServiceApplication.class, args);
    }

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", false);
    }

    @RabbitListener(queues = "myQueue")
    public void listen(MessageRequest messageRequest) {
        log.info("Message received on MyQueue");
        log.debug("Message read from myQueue : {}", messageRequest);
    }

}
