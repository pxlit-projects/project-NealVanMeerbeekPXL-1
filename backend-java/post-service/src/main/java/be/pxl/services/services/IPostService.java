package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.controller.request.NewPostRequest;
import be.pxl.services.controller.request.ReviewPostRequest;
import be.pxl.services.controller.request.UpdatePostRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface IPostService {
    List<PostDTO> getAllPosts();
    PostDTO getPostById(String postId);
    PostDTO addPost(NewPostRequest newPostRequest);
    void publishPost(String id);
    PostDTO updatePost(String id, UpdatePostRequest newPostRequest);
    void updatePostReviewStatus(String id, ReviewPostRequest reviewPostRequest);
}
