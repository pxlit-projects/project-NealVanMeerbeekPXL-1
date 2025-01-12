package be.pxl.services.services;

import be.pxl.services.controller.dto.CommentDTO;
import be.pxl.services.controller.request.CreateCommentRequest;

import java.util.List;

public interface ICommentService {
    List<CommentDTO> getCommentsByPostIdSortedByCreationDate(String postId);
    CommentDTO addComment(String postId, CreateCommentRequest createCommentRequest);
//    void addReview(NewReviewRequest newReviewRequest);
//    List<ReviewDTO> getAllReviews();
//    ReviewDTO getReviewById(String id);
//    Review doReview(String id, DoReviewRequest reviewRequest);
//    List<PostDTO> getAllPosts();
//    PostDTO getPostById(String postId);
//    PostDTO addPost(NewPostRequest newPostRequest);
//    void publishPost(String id);
//    PostDTO updatePost(String id, UpdatePostRequest newPostRequest);
}
