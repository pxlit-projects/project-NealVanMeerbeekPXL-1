package be.pxl.services.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
	private final String resourceName;
    private final String id;

    public ResourceAlreadyExistsException(String resourceName, Object id) {
        super(String.format("%s already exists with id '%s'", resourceName, id));
        this.resourceName = resourceName;
        this.id = id.toString();
        log.debug("409 Conflict: {}", getMessage());
    }

}
