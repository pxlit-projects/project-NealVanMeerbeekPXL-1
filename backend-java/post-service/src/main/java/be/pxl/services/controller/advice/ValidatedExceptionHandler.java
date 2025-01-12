package be.pxl.services.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ValidatedExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponseBody> handleValidationExceptions(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult().getAllErrors().stream()
				.map(error -> {
					FieldError fieldError = (FieldError) error;
					return String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
				})
				.toList();

		log.debug("400 Bad Request: Validation failed. {}", errors);
		return new ResponseEntity<>(new ErrorResponseBody("Validation failed.", errors), HttpStatus.BAD_REQUEST);
	}

	private record ErrorResponseBody(String message, List<String> errors) {
	}
}
