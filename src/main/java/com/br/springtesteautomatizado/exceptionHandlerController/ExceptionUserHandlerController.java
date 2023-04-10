package com.br.springtesteautomatizado.exceptionHandlerController;

import com.br.springtesteautomatizado.exceptions.CpfAlreadyExistException;
import com.br.springtesteautomatizado.exceptions.InvalidCpfException;
import com.br.springtesteautomatizado.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionUserHandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CpfAlreadyExistException.class)
    public final ResponseEntity<ExceptionResponse> handleCpfAlreadyExistException(CpfAlreadyExistException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidCpfException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidCpfException(InvalidCpfException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
