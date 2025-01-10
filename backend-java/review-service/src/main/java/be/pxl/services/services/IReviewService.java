package be.pxl.services.services;

import be.pxl.services.controller.request.NewReviewRequest;

public interface IReviewService {
    void addReview(NewReviewRequest newReviewRequest);
//    List<PostDTO> getAllPosts();
//    PostDTO getPostById(String postId);
//    PostDTO addPost(NewPostRequest newPostRequest);
//    void publishPost(String id);
//    PostDTO updatePost(String id, UpdatePostRequest newPostRequest);
}
