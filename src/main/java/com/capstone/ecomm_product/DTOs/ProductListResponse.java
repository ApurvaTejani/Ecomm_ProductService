package com.capstone.ecomm_product.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponse {
    private List<ProductResponseDTO> responseDTOList;

    public ProductListResponse() {
        this.responseDTOList = new ArrayList<>();
    }
}
