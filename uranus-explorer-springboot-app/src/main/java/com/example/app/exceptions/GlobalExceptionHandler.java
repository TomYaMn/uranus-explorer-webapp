package com.example.app.exceptions;

import com.example.app.dto.ErrorResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle security-related exceptions
    @ExceptionHandler({
            BadCredentialsException.class,
            AccountStatusException.class,
            AccessDeniedException.class,
            SignatureException.class,
            ExpiredJwtException.class
    })
    public ProblemDetail handleSecurityExceptions(Exception exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());

        // Customize descriptions for specific exceptions
        if (exception instanceof BadCredentialsException) {
            errorDetail.setProperty("description", "The username or password is incorrect");
        } else if (exception instanceof AccountStatusException) {
            errorDetail.setProperty("description", "The account is locked");
        } else if (exception instanceof AccessDeniedException) {
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        } else if (exception instanceof SignatureException) {
            errorDetail.setProperty("description", "The JWT signature is invalid");
        } else if (exception instanceof ExpiredJwtException) {
            errorDetail.setProperty("description", "The JWT token has expired");
        }

        return errorDetail;
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal argument exception: ", ex);

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle database-related exceptions
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleDatabaseException(DataAccessException ex, WebRequest request) {
        log.error("Database error: ", ex);

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Database error occurred: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Fallback handler for general exceptions
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex, WebRequest request) {
        // Log the exception for better debugging
        log.error("An unexpected error occurred: ", ex);

        // Create a ProblemDetail with error information
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        problemDetail.setProperty("error", ex.getMessage());
        problemDetail.setProperty("exception", ex.getClass().getSimpleName());
        problemDetail.setProperty("request", request.getDescription(false));

        return problemDetail;
    }
}
