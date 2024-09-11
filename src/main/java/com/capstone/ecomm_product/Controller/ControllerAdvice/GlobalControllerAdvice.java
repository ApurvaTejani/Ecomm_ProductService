package com.capstone.ecomm_product.Controller.ControllerAdvice;

import com.capstone.ecomm_product.DTOs.ErrorResponseDTO;
import com.capstone.ecomm_product.Exception.CategoryNotFoundException;
import com.capstone.ecomm_product.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(Exception e){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setMessage(e.getMessage());
        errorResponseDTO.setMessageCode(404);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

}
