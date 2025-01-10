package be.pxl.services.services;

import be.pxl.services.builder.PostBuilder;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.domain.Post;
import be.pxl.services.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {
    @Mock
    private PostRepository postRepository;

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
    void addPost() {
    }
}