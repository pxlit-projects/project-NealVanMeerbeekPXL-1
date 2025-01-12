package be.pxl.services.repository;

import be.pxl.services.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PublicPostRepository extends JpaRepository<Post, UUID> {
    Set<Post> findAllByPublishedTrue();
    Optional<Post> findByIdAndPublishedTrue(UUID id);
}
