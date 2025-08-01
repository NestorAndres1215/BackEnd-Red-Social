package com.na.backend.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String unsupported = ex.getContentType() != null ? ex.getContentType().toString() : "unknown";
        List<String> supported = ex.getSupportedMediaTypes().stream()
                .map(MediaType::toString)
                .collect(Collectors.toList());

        String message = String.format("Content-Type '%s' is not supported. Supported types: %s",
                unsupported, String.join(", ", supported));

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(message);
    }
}