package com.capstone.ecomm_product.Controller.ControllerAdvice;

import com.capstone.ecomm_product.DTOs.ErrorResponseDTO;
import com.capstone.ecomm_product.Exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(String message, int code, HttpStatus status) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(message);
        errorResponseDTO.setMessageCode(code);
        return new ResponseEntity<>(errorResponseDTO, status);
    }

    @ExceptionHandler({BadRequestClient.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponseDTO> handleBadRequest(Exception e){
        return buildErrorResponse(e.getMessage(),400,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNoFoundRequest(ResourceNotFoundException e){
        return buildErrorResponse(e.getMessage(),404,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Request body is missing or invalid", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponseDTO> handleAPIException(APIException e){
        return buildErrorResponse(e.getMessage(),400,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleTransactionSystemException(TransactionSystemException e){
        return new ResponseEntity<>("ConstraintViolation =Category Name should be minimum of 5",HttpStatus.BAD_REQUEST);
    }


}
