package com.capstone.ecomm_product.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponse {
    private List<ProductResponseDTO> responseDTOList;

}
