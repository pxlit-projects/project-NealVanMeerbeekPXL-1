package be.pxl.services.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.status());
        String message = response.reason(); // Get the original message

        if (httpStatus.is4xxClientError()) {
            return new ResponseStatusException(httpStatus, message);
        } else if (httpStatus.is5xxServerError()) {
            return new ResponseStatusException(httpStatus, "Internal Server Error from the remote service");
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
