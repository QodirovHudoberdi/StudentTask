package com.company.response;

import com.company.models.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> on(NotFoundException e) {
        final ResponseMessage result = new ResponseMessage(404,"Not Found", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> on(AlreadyExistException e) {
        final ResponseMessage result = new ResponseMessage(250,"Already exist", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(WrongException.class)
    public ResponseEntity<?> on(WrongException e) {
        final ResponseMessage result = new ResponseMessage(303,"Something went wrong", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @ExceptionHandler(OkResponse.class)
    public ResponseEntity<?> on(OkResponse e) {
        final ResponseMessage result = new ResponseMessage(200, "All successfully done ", e.getMessage());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
}