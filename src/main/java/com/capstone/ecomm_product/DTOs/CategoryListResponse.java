package com.capstone.ecomm_product.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryListResponse {
    private List<CategoryResponseDTO> categoryResponseDTOS;

    public CategoryListResponse() {
        this.categoryResponseDTOS = new ArrayList<>();
    }
}
