package be.pxl.services.controller.advice;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class FeignExceptionAdvice {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException exception) {
        log.debug("Feign exception occurred:", exception);

        return ResponseEntity.status(exception.status()).body(exception.contentUTF8());
    }
}