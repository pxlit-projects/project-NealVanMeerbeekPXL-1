package be.pxl.services.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final String requestedFieldValue;
    private final String reason;

    public DomainException(String resourceName, String fieldName, Object requestedFieldValue, String reason) {
        super(String.format("%s of %s can't be set to '%s'. Reason: %s", resourceName, fieldName, requestedFieldValue, reason));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.requestedFieldValue = requestedFieldValue.toString();
        this.reason = reason;
        log.debug("400 Bad Request: {}", getMessage());
    }
}
