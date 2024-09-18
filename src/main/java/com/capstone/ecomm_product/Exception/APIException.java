package com.capstone.ecomm_product.Exception;

public class APIException extends RuntimeException{
    public APIException(){

    }
    public APIException(String message){
        super(message);
    }
}
