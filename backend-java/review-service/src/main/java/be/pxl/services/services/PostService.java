package be.pxl.services.services;

import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.exception.ResourceAlreadyExistsException;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository postRepository;

    @Override
    public void addPost(NewPostRequest newPostRequest) {
        postRepository.findById(UUID.fromString(newPostRequest.getId())).ifPresent(post -> {
            throw new ResourceAlreadyExistsException("Post", newPostRequest.getId());
        });

        Post post = Post.builder()
                .id(UUID.fromString(newPostRequest.getId()))
                .creationDate(newPostRequest.getCreationDate())
                .title(newPostRequest.getTitle())
                .author(newPostRequest.getAuthor())
                .content(newPostRequest.getContent())
                .build();
        postRepository.save(post);
    }

}
