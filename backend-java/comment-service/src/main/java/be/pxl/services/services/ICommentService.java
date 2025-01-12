package be.pxl.services.services;

import be.pxl.services.controller.dto.CommentDTO;
import be.pxl.services.controller.request.CreateCommentRequest;
import be.pxl.services.controller.request.UpdateCommentRequest;

import java.util.List;

public interface ICommentService {
    CommentDTO addComment(String postId, CreateCommentRequest createCommentRequest);
    CommentDTO updateComment(String id, UpdateCommentRequest updateCommentRequest);
    void deleteComment(String id);
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
