package be.pxl.services.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class PendingReviewException extends RuntimeException {
    public PendingReviewException() {
        super("This post has a pending review");
        log.debug("409 Conflict: {}", getMessage());
    }

}
