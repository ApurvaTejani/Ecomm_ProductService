package com.capstone.ecomm_product.Exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    UUID fieldId;

    public ResourceNotFoundException(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException( String resourceName, String field, String fieldName) {
        super(String.format("%s having %s - Reason %s",resourceName,field,fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException( String resourceName, String field, UUID fieldId) {
        super(String.format("%s not found %s with id - %s",resourceName,field,fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId=fieldId;
    }
}
