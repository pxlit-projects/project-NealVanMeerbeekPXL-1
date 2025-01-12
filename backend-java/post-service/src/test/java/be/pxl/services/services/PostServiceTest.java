package be.pxl.services.services;

import be.pxl.services.builder.PostBuilder;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.ReviewStatus;
import be.pxl.services.exception.DomainException;
import be.pxl.services.exception.ResourceAlreadyExistsException;
import be.pxl.services.rabbitmq.PostPublishedMessage;
import be.pxl.services.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static be.pxl.services.builder.PostBuilder.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PostService postService;

    private final List<Post> POSTS = List.of(PostBuilder.builder().build(), PostBuilder.builder().build());

    @Test
    void getAllPosts() {
        when(postRepository.findAll()).thenReturn(POSTS);

        List<PostDTO> posts = postService.getAllPosts();

        assertEquals(POSTS.size(), posts.size());
        Post expectedFirstPost = POSTS.get(0);
        PostDTO actualFirstPost = posts.get(0);
        assertEquals(expectedFirstPost.getId(), actualFirstPost.getId());
        assertEquals(expectedFirstPost.getTitle(), actualFirstPost.getTitle());
        assertEquals(expectedFirstPost.getContent(), actualFirstPost.getContent());
        assertEquals(expectedFirstPost.getAuthor(), actualFirstPost.getAuthor());
    }

    @Test
    void getPostById() {
        Post post = PostBuilder.builder().build();
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

        PostDTO postDTO = postService.getPostById(post.getId().toString());

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getAuthor(), postDTO.getAuthor());
    }

    @Test
    void addPost() {
        Post post = PostBuilder.builder().build();
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.empty());
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDTO postDTO = postService.addPost(NewPostRequest.builder().id(String.valueOf(post.getId())).title(TITLE).author(AUTHOR).content(CONTENT).build());

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getAuthor(), postDTO.getAuthor());
    }

    @Test
    void addPostThrowsResourceAlreadyExistsException() {
        Post post = PostBuilder.builder().build();
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

        assertThrows(ResourceAlreadyExistsException.class, () -> postService.addPost(NewPostRequest.builder().id(String.valueOf(post.getId())).title(TITLE).author(AUTHOR).content(CONTENT).build()));
    }

    @Test
    void publishPostThrowsDomainException() {
        Post post = PostBuilder.builder().build();
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

        assertThrows(DomainException.class, () -> postService.publishPost(post.getId().toString()));
        assertFalse(post.isPublished());
        verify(rabbitTemplate, times(0)).convertAndSend(anyString(), Optional.ofNullable(any()));
    }

    @Test
    void publishPostPublishesApprovedPost() {
        Post post = PostBuilder.builder().build();
        post.setReviewStatus(ReviewStatus.APPROVED);
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

        postService.publishPost(post.getId().toString());

        assertTrue(post.isPublished());
        verify(postRepository, times(1)).save(post);
        verify(rabbitTemplate, times(1)).convertAndSend("publishPostQueue", PostPublishedMessage.builder().postId(post.getId().toString()).build());
    }

    @Test
    void updatePost() {
        Post post = PostBuilder.builder().build();
        post.setReviewStatus(ReviewStatus.NOT_YET_PERFORMED);
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDTO postDTO = postService.updatePost(post.getId().toString(), UpdatePostRequest.builder().title(TITLE).author(AUTHOR).content(CONTENT).build());

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getAuthor(), postDTO.getAuthor());
    }

    @Test
    void updatePostThrowsDomainException() {
        Post post = PostBuilder.builder().build();
        post.setReviewStatus(ReviewStatus.APPROVED);
        when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

        assertThrows(DomainException.class, () -> postService.updatePost(post.getId().toString(), UpdatePostRequest.builder().title(TITLE).author(AUTHOR).content(CONTENT).build()));
        verify(postRepository, times(0)).save(any(Post.class));
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(postRepository, rabbitTemplate);
    }
}