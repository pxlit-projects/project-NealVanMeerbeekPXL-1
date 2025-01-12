package be.pxl.services.domain;

import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.RabbitMQContainer;

import java.util.UUID;

import static be.pxl.services.builder.PostBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.jpa.hibernate.ddl-auto=update"
})
@Testcontainers
@AutoConfigureMockMvc
public class PostIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

    @Container
    private static final RabbitMQContainer myRabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:4-management-alpine"));

    @DynamicPropertySource
    public static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @DynamicPropertySource
    public static void registerRabbitMQProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.port", myRabbitMQContainer::getAmqpPort);
    }

    @Test
    public void testPostIsAddedToRepositoryWhenAllConstraintsAreSatisfied() throws Exception {
        NewPostRequest newPostRequest = NewPostRequest.builder()
                .id(UUID.randomUUID().toString())
                .title(TITLE)
                .author(AUTHOR)
                .content(CONTENT)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .header("X-USER-ROLE", "ADMIN")
                        .content(objectMapper.writeValueAsString(newPostRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertEquals(1, postRepository.findAll().size());
    }

    @Test
    public void testPostIsNotAddedToRepositoryWhenIdAlreadyExists() throws Exception {
        UUID postID = UUID.randomUUID();
        postRepository.save(Post.builder().id(postID).build());
        NewPostRequest newPostRequest = NewPostRequest.builder()
                .id(postID.toString())
                .title(TITLE)
                .author(AUTHOR)
                .content(CONTENT)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .header("X-USER-ROLE", "ADMIN")
                        .content(objectMapper.writeValueAsString(newPostRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
        assertEquals(1, postRepository.findAll().size());
    }

    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }
}