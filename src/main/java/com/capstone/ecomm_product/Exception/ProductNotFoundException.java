package com.capstone.ecomm_product.Exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
