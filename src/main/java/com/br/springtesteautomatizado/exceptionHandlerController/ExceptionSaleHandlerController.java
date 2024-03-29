package com.br.springtesteautomatizado.exceptionHandlerController;

import com.br.springtesteautomatizado.exceptions.DuplicateProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionSaleHandlerController {
    @ExceptionHandler(DuplicateProductException.class)
    public final ResponseEntity<ExceptionResponse> handleDuplicateProductException(DuplicateProductException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
