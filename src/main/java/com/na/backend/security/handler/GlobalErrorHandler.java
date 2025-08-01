package com.na.backend.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.na.backend.message.BaseMessage;

@ControllerAdvice
public class GlobalErrorHandler {

    public ResponseEntity<String>handleInternalServerError(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseMessage.ERROR_INTERNO.getMensaje());
    }

}
