package com.seyha.taskflow.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class BaseController {

  /** The main builder for a success response with a payload. */
  protected <T> ResponseEntity<ApiStructureResponse<T>> response(
      HttpStatus status, String message, T data) {
    ApiStructureResponse<T> body =
        ApiStructureResponse.<T>builder()
            .message(message)
            .payload(data)
            .status(status)
            .timestamp(Instant.now())
            .build();
    return new ResponseEntity<>(body, status);
  }

  /** The main builder for a success response without a payload. */
  protected <T> ResponseEntity<ApiStructureResponse<T>> response(
      HttpStatus status, String message) {
    ApiStructureResponse<T> body =
        ApiStructureResponse.<T>builder()
            .message(message)
            .status(status)
            .timestamp(Instant.now())
            .build();
    return new ResponseEntity<>(body, status);
  }

  // --- NEW HELPER METHODS ---

  /** Builds a 200 OK response with a payload. */
  protected <T> ResponseEntity<ApiStructureResponse<T>> ok(String message, T data) {
    return response(HttpStatus.OK, message, data);
  }

  /** Builds a 200 OK response without a payload. */
  protected <T> ResponseEntity<ApiStructureResponse<T>> ok(String message) {
    return response(HttpStatus.OK, message);
  }

  /** Builds a 201 Created response with a payload. */
  protected <T> ResponseEntity<ApiStructureResponse<T>> created(String message, T data) {
    return response(HttpStatus.CREATED, message, data);
  }
}
