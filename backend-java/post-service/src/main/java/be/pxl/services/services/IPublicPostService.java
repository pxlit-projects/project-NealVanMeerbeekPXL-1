package be.pxl.services.services;

import be.pxl.services.controller.dto.PublicPostDTO;

import java.util.List;

public interface IPublicPostService {
    List<PublicPostDTO> getAllPosts();
    PublicPostDTO getPostById(String id);
}
