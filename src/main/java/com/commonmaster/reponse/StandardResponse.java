package com.commonmaster.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Standard response wrapper for all API responses
 * Provides consistent response structure across the application
 *
 * @param <T> The type of data being returned
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class StandardResponse<T> {

    /**
     * Indicates if the operation was successful
     */
    private boolean success;

    /**
     * Human-readable message describing the operation result
     */
    private String message;

    /**
     * The actual data payload
     */
    private T data;

    /**
     * Log ID for tracking this specific operation
     */
    private String logId;

    /**
     * Request ID for tracking the entire request
     */
    private String requestId;

    /**
     * Timestamp when the response was generated
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;

    /**
     * Error details (only present when success = false)
     */
    private ErrorDetails error;

    /**
     * Additional metadata about the operation
     */
    private ResponseMetadata metadata;

    /**
     * Error details nested class
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class ErrorDetails {
        private String code;
        private String message;
        private String details;
        private String field;
    }

    /**
     * Response metadata nested class
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseMetadata {
        private Long totalRecords;
        private Integer currentPage;
        private Integer pageSize;
        private Integer totalPages;
        private Long executionTimeMs;
        private String operation;
    }

    /**
     * Creates a successful response with data
     *
     * @param data The response data
     * @param message Success message
     * @param <T> Type of data
     * @return StandardResponse with success = true
     */
    public static <T> StandardResponse<T> success(T data, String message) {
        StandardResponse<T> response = new StandardResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    /**
     * Creates a successful response with data and metadata
     *
     * @param data The response data
     * @param message Success message
     * @param metadata Response metadata
     * @param <T> Type of data
     * @return StandardResponse with success = true and metadata
     */
    public static <T> StandardResponse<T> success(T data, String message, ResponseMetadata metadata) {
        StandardResponse<T> response = new StandardResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setMetadata(metadata);
        return response;
    }

    /**
     * Creates a successful response with just a message
     *
     * @param message Success message
     * @return StandardResponse with success = true
     */
    public static StandardResponse<Void> success(String message) {
        StandardResponse<Void> response = new StandardResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

    /**
     * Creates an error response
     *
     * @param message Error message
     * @param errorCode Error code
     * @param errorDetails Additional error details
     * @return StandardResponse with success = false
     */
    public static StandardResponse<Void> error(String message, String errorCode, String errorDetails) {
        StandardResponse<Void> response = new StandardResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setError(new ErrorDetails(errorCode, message, errorDetails, null));
        return response;
    }

    /**
     * Creates an error response with field-specific error
     *
     * @param message Error message
     * @param errorCode Error code
     * @param field Field that caused the error
     * @param errorDetails Additional error details
     * @return StandardResponse with success = false and field error
     */
    public static StandardResponse<Void> error(String message, String errorCode, String field, String errorDetails) {
        StandardResponse<Void> response = new StandardResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());
        response.setError(new ErrorDetails(errorCode, message, errorDetails, field));
        return response;
    }
}