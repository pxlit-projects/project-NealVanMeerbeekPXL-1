package be.pxl.services.services;

import be.pxl.services.controller.dto.ReviewDTO;
import be.pxl.services.controller.request.DoReviewRequest;
import be.pxl.services.controller.request.NewReviewRequest;
import be.pxl.services.domain.Review;

import java.util.List;

public interface IReviewService {
    void addReview(NewReviewRequest newReviewRequest);
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReviewById(String id);
    Review doReview(String id, DoReviewRequest reviewRequest);
//    List<PostDTO> getAllPosts();
//    PostDTO getPostById(String postId);
//    PostDTO addPost(NewPostRequest newPostRequest);
//    void publishPost(String id);
//    PostDTO updatePost(String id, UpdatePostRequest newPostRequest);
}
