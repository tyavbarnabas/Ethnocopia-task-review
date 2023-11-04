package com.ethnocopia.exception;

import com.ethnocopia.constants.GeneralResponseEnum;
import com.ethnocopia.dto.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomerResponse> handlePayloadException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new CustomerResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getAllErrors().get(0).getDefaultMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(new CustomerResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.NOT_FOUND);
    }


}
