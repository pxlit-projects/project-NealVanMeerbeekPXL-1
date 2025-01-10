package be.pxl.services.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ValidatedExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponseBody> handleValidationExceptions(MethodArgumentNotValidException exception) {
		List<String> errors = new ArrayList<>();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		log.debug("400 Bad Request: Validation failed. {}", errors);
		return new ResponseEntity<>(new ErrorResponseBody("Validation failed.", errors), HttpStatus.BAD_REQUEST);
	}

	private record ErrorResponseBody(String message, List<String> errors) {
	}

}
