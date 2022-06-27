package org.george.moviecriticapi.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.george.moviecriticapi.exception.InvalidRequestException;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionError> handleInvalidRequestException(
            InvalidRequestException e,
            HttpServletRequest req) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionError.builder()
                        .errorStatus(HttpStatus.NOT_FOUND.toString())
                        .errorMessage(APIMessages.INVALID_REQUEST_MSG)
                        .errorDescription(e.getMessage())
                        .erroPath(req.getRequestURI())
                        .build()
        );
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    protected static class ExceptionError {
        private String errorStatus;
        private String errorMessage;
        private String errorDescription;
        private String erroPath;
    }
}
