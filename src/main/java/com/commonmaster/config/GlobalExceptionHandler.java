package com.commonmaster.config;

import com.commonmaster.reponse.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardResponse<?>> handleRuntimeException(RuntimeException ex) {
        logger.error("Unhandled RuntimeException: {}", ex.getMessage(), ex);
        StandardResponse<Void> response = StandardResponse.error(
                ex.getMessage() != null ? ex.getMessage() : "Internal server error",
                "RUNTIME_EXCEPTION",
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

