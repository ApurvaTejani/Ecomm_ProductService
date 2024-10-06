package com.capstone.ecomm_product.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponse {
    private List<ProductResponseDTO> responseDTOList;
    private Integer pageNumber;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPage;

    private boolean lastPage;
    public ProductListResponse() {
        this.responseDTOList = new ArrayList<>();
    }
}
