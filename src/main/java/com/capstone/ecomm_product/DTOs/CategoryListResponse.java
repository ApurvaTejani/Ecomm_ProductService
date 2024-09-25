package com.capstone.ecomm_product.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryListResponse {
    private List<CategoryResponseDTO> categoryResponseDTOS;

    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPage;

    private boolean lastPage;
    public CategoryListResponse() {
        this.categoryResponseDTOS = new ArrayList<>();
    }
}
