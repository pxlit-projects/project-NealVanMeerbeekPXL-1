package be.pxl.services.services;

import be.pxl.services.controller.request.NewPostRequest;

public interface IPostService {
    void addPost(NewPostRequest newPostRequest);
}
