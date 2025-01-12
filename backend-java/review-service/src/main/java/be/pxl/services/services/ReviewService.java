package be.pxl.services.services;

import be.pxl.services.controller.dto.ReviewDTO;
import be.pxl.services.controller.request.DoReviewRequest;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.domain.Comment;
import be.pxl.services.domain.Post;
import be.pxl.services.domain.Review;
import be.pxl.services.exception.PendingReviewException;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewDTO::new).toList();
    }

    @Override
    public ReviewDTO getReviewById(String id) {
        return reviewRepository.findById(UUID.fromString(id)).map(ReviewDTO::new).orElseThrow(() -> new ResourceNotFoundException("Review", "ID", id));
    }

    @Override
    public void addReview(NewReviewRequest newReviewRequest) {
        reviewRepository.findByReviewDateIsNullAndPostId(UUID.fromString(newReviewRequest.getId())).ifPresent(review -> {
            throw new PendingReviewException();
        });

        Post post = Post.builder()
                .id(UUID.fromString(newReviewRequest.getId()))
                .creationDate(newReviewRequest.getCreationDate())
                .title(newReviewRequest.getTitle())
                .author(newReviewRequest.getAuthor())
                .content(newReviewRequest.getContent())
                .build();

        reviewRepository.save(Review.builder()
                .id(UUID.randomUUID())
                .post(post)
                .build());
    }

    @Override
    public Review doReview(String id, DoReviewRequest reviewRequest) {
        return reviewRepository.findById(UUID.fromString(id)).map(review -> {
            review.setReviewDate(LocalDateTime.now());
            reviewRequest.getComments().stream().map(commentRequest -> Comment.builder().review(review).content(commentRequest.getContent()).build()).toList().forEach(review::addComment);
            return reviewRepository.save(review);
        }).orElseThrow(() -> new ResourceNotFoundException("Review", "ID", id));
    }

//    @Override
//    public List<PostDTO> getAllPosts() {
//        return postRepository.findAll().stream().map(PostDTO::new).toList();
//    }
//
//    @Override
//    public PostDTO getPostById(String postId) {
//        return postRepository.findById(UUID.fromString(postId)).map(PostDTO::new).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
//    }
//
//    @Override
//    public PostDTO addPost(NewPostRequest newPostRequest) {
//        Post post = Post.builder()
//                .id(UUID.fromString(newPostRequest.getId()))
//                .creationDate(newPostRequest.getCreationDate())
//                .title(newPostRequest.getTitle())
//                .author(newPostRequest.getAuthor())
//                .content(newPostRequest.getContent())
//                .build();
//        Post newPost = postRepository.save(post);
//
////        NotificationRequest notificationRequest = NotificationRequest.builder()
////                .sender("employee-service")
////                .recipient("notification-service")
////                .subject("Database Operation")
////                .message("Employee Created")
////                .build();
////        notificationClient.sendNotification(notificationRequest);
//
//        return new PostDTO(newPost);
//    }
//
//    @Override
//    public void publishPost(String postId) {
//        Post post = postRepository.findById(UUID.fromString(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
//        post.publish();
//        postRepository.save(post);
//    }
//
//    @Override
//    public PostDTO updatePost(String postId, UpdatePostRequest updatePostRequest) {
//        return postRepository.findById(UUID.fromString(postId)).map(post -> {
//            post.setTitle(updatePostRequest.getTitle());
//            post.setAuthor(updatePostRequest.getAuthor());
//            post.setContent(updatePostRequest.getContent());
//            Post updatedPost = postRepository.save(post);
//            return new PostDTO(updatedPost);
//        }).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
//    }

}
