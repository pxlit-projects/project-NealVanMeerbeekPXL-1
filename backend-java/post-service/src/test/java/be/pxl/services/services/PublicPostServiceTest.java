package be.pxl.services.services;

import be.pxl.services.builder.PostBuilder;
import be.pxl.services.controller.dto.PublicPostDTO;
import be.pxl.services.domain.Post;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.PublicPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicPostServiceTest {
    @Mock
    private PublicPostRepository publicPostRepository;

    @InjectMocks
    private PublicPostService publicPostService;
    private final Post POST = PostBuilder.builder().build();
    private final Set<Post> POSTS = Set.of(POST, PostBuilder.builder().build());

    @Test
    void getAllPosts() {
        when(publicPostRepository.findAllByPublishedTrue()).thenReturn(POSTS);

        List<PublicPostDTO> posts = publicPostService.getAllPosts();

        assertEquals(POSTS.size(), posts.size());
        PublicPostDTO actualFirstPost = posts.stream().filter(post -> post.getId().equals(POST.getId())).findFirst().orElseThrow();
        assertEquals(POST.getId(), actualFirstPost.getId());
        assertEquals(POST.getTitle(), actualFirstPost.getTitle());
        assertEquals(POST.getContent(), actualFirstPost.getContent());
        assertEquals(POST.getAuthor(), actualFirstPost.getAuthor());
    }

    @Test
    void getPostById() {
        Post post = PostBuilder.builder().build();
        when(publicPostRepository.findByIdAndPublishedTrue(post.getId())).thenReturn(Optional.of(post));

        PublicPostDTO postDTO = publicPostService.getPostById(post.getId().toString());

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getAuthor(), postDTO.getAuthor());
    }

    @Test
    void getPostByIdThrowsResourceNotFoundException() {
        when(publicPostRepository.findByIdAndPublishedTrue(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> publicPostService.getPostById(UUID.randomUUID().toString()));
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(publicPostRepository);
    }
}