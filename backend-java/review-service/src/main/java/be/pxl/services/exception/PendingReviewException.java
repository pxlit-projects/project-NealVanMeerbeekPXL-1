package be.pxl.services.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class PendingReviewException extends RuntimeException {
	private final String resourceName;
    private final String id;

    public PendingReviewException(String postName, Object reviewId) {
        super(String.format("Post with name %s still has a previous review pending with id '%s'", postName, reviewId));
        this.resourceName = postName;
        this.id = reviewId.toString();
        log.debug("409 Conflict: {}", getMessage());
    }

}
