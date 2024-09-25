package com.capstone.ecomm_product.DTOs;

import com.capstone.ecomm_product.Models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDTO {

    private UUID id;

    private String categoryName;



}
