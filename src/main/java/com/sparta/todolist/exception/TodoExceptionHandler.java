package com.sparta.todolist.exception;

import com.sparta.todolist.exception.message.ErrorMessage;
import com.sparta.todolist.exception.message.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class TodoExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorMessage> handleInvalidPasswordException(InvalidPasswordException ex) {
        log.error(ex.getMessage());
        ErrorMessage response = ErrorMessage.createErrorResponse(StatusEnum.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleTodoNotFoundException(TodoNotFoundException ex) {
        log.error(ex.getMessage());
        ErrorMessage response = ErrorMessage.createErrorResponse(StatusEnum.NOT_FOUND,  ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        // DefaultMessage 만들기
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        String combinedErrorMessage = String.join(", ", errors.values());

        ErrorMessage response = ErrorMessage.createErrorResponse(StatusEnum.BAD_REQUEST, combinedErrorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        ErrorMessage response = ErrorMessage.createErrorResponse(StatusEnum.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
