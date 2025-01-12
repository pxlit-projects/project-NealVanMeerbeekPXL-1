package be.pxl.services.repository;

import be.pxl.services.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Optional<Review> findByReviewDateIsNullAndPostId(UUID postId);
    List<Review> findAllByReviewDateIsNull();
}
