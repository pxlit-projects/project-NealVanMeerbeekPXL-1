package be.pxl.services.repository;

import be.pxl.services.domain.Comment;
import be.pxl.services.domain.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
