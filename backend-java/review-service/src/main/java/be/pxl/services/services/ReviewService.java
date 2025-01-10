package be.pxl.services.services;

import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    // private final NotificationClient notificationClient;

    @Override
    public void addReview(NewReviewRequest newReviewRequest) {

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
