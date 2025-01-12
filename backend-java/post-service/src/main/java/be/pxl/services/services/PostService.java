package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.controller.request.ReviewPostRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.ReviewStatus;
import be.pxl.services.exception.DomainException;
import be.pxl.services.exception.ResourceAlreadyExistsException;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.rabbitmq.PostPublishedMessage;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostDTO::new).toList();
    }

    @Override
    public PostDTO getPostById(String postId) {
        return postRepository.findById(UUID.fromString(postId)).map(PostDTO::new).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
    }

    @Override
    public PostDTO addPost(NewPostRequest newPostRequest) {
        postRepository.findById(UUID.fromString(newPostRequest.getId())).ifPresent(post -> {
            throw new ResourceAlreadyExistsException("Post", newPostRequest.getId());
        });

        Post post = Post.builder()
                .id(UUID.fromString(newPostRequest.getId()))
                .title(newPostRequest.getTitle())
                .author(newPostRequest.getAuthor())
                .content(newPostRequest.getContent())
                .build();
        Post newPost = postRepository.save(post);

        return new PostDTO(newPost);
    }

    @Override
    public void publishPost(String postId) {
        Post post = postRepository.findById(UUID.fromString(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        post.publish();
        postRepository.save(post);
        rabbitTemplate.convertAndSend("publishPostQueue", PostPublishedMessage.builder().postId(postId).build());
    }

    @Override
    public PostDTO updatePost(String postId, UpdatePostRequest updatePostRequest) {
        return postRepository.findById(UUID.fromString(postId)).map(post -> {
            if (post.getReviewStatus() != ReviewStatus.NOT_YET_PERFORMED) {
                throw new DomainException("Post", "a post can't be changed after being submitted");
            }

            post.setTitle(updatePostRequest.getTitle());
            post.setAuthor(updatePostRequest.getAuthor());
            post.setContent(updatePostRequest.getContent());
            Post updatedPost = postRepository.save(post);
            return new PostDTO(updatedPost);
        }).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
    }

    @Override
    public PostDTO updatePostBeforeReview(String postId, UpdatePostRequest updatePostRequest) {
        return postRepository.findById(UUID.fromString(postId)).map(post -> {
            if (post.getReviewStatus() != ReviewStatus.NOT_YET_PERFORMED) {
                throw new DomainException("Post", "a post can't be changed after being submitted");
            }

            post.setTitle(updatePostRequest.getTitle());
            post.setAuthor(updatePostRequest.getAuthor());
            post.setContent(updatePostRequest.getContent());
            post.setReviewStatus(ReviewStatus.PENDING);
            Post updatedPost = postRepository.save(post);
            return new PostDTO(updatedPost);
        }).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
    }

    @Override
    public void updatePostReviewStatus(String id, ReviewPostRequest reviewPostRequest) {
        postRepository.findById(UUID.fromString(id)).map(post -> {
            post.setReviewStatus(reviewPostRequest.reviewStatus());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id));
    }

}
