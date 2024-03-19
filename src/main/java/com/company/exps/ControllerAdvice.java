package com.company.exps;

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
        final ResponseMessage result = new ResponseMessage(250,"Already Have ", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @ExceptionHandler(MistakeException.class)
    public ResponseEntity<?> on(MistakeException e) {
        final ResponseMessage result = new ResponseMessage(303,"Something went wrong", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    @ExceptionHandler(OkExceptions.class)
    public ResponseEntity<?> on(OkExceptions e) {
        final ResponseMessage result = new ResponseMessage(200, "Alright", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
}
