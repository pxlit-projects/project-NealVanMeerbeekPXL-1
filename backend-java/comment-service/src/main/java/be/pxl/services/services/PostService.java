package be.pxl.services.services;

import be.pxl.services.controller.dto.CommentDTO;
import be.pxl.services.controller.dto.PostDTO;
import be.pxl.services.domain.Post;
import be.pxl.services.repository.CommentRepository;
import be.pxl.services.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addPost(String id) {
        UUID postId = UUID.fromString(id);
        if (postRepository.findById(postId).isEmpty()) {
            postRepository.save(Post.builder().id(postId).build());
        }
    }

    @Override
    public PostDTO getPostById(String id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "creationDate");
        List<CommentDTO> commentDTOS = commentRepository.findAllByPostId(UUID.fromString(id), sort).stream().map(CommentDTO::new).toList();
        return new PostDTO(UUID.fromString(id), commentDTOS);
    }
}
