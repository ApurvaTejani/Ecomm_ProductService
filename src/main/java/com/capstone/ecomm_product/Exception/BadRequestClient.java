package com.capstone.ecomm_product.Exception;

import java.util.UUID;

public class BadRequestClient extends Exception{
    String resourceName;
    String field;
    String fieldName;
    UUID fieldId;

    public BadRequestClient(String resourceName, String field, UUID fieldId) {
        super(String.format("%s validation failed due to %s - %s",resourceName,field,fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    public BadRequestClient( String resourceName, String field, String fieldName) {
        super(String.format("%s validation failed due to %s - %s",resourceName,field,fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
}
