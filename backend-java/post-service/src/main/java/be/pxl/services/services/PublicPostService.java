package be.pxl.services.services;

import be.pxl.services.controller.dto.PublicPostDTO;
import be.pxl.services.exception.ResourceNotFoundException;
import be.pxl.services.repository.PublicPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicPostService implements IPublicPostService {
    private final PublicPostRepository publicPostRepository;

    @Override
    public List<PublicPostDTO> getAllPosts() {
        return publicPostRepository.findAllByPublishedTrue().stream().map(PublicPostDTO::new).toList();
    }

    @Override
    public PublicPostDTO getPostById(String postId) {
        return publicPostRepository.findByIdAndPublishedTrue(UUID.fromString(postId)).map(PublicPostDTO::new).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
    }
}
