package be.pxl.services.services;

import be.pxl.services.controller.dto.PostDTO;

public interface IPostService {
    PostDTO getPostById(String postId);
    void addPost(String postId);
}
