package be.pxl.services.controller;

import be.pxl.services.controller.dto.PublicPostDTO;
import be.pxl.services.services.IPublicPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/post")
@RequiredArgsConstructor
@Slf4j
public class PublicPostController {
    private final IPublicPostService publicPostService;

    @GetMapping
    public List<PublicPostDTO> findAll() {
        log.info("GET /public/post");
        return publicPostService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PublicPostDTO findById(@PathVariable String id) {
        log.info("GET /public/post/{id}");
        return publicPostService.getPostById(id);
    }
}
