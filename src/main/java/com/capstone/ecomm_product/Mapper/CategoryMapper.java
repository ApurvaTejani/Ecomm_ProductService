package com.capstone.ecomm_product.Mapper;

import com.capstone.ecomm_product.DTOs.CategoryListResponse;
import com.capstone.ecomm_product.DTOs.CategoryRequestDTO;
import com.capstone.ecomm_product.DTOs.CategoryResponseDTO;
import com.capstone.ecomm_product.Models.Category;

import java.util.Date;
import java.util.List;

public class CategoryMapper {

    public static CategoryListResponse convertCategoryListToCategoryListResponseDTO(List<Category> categoryList){
        CategoryListResponse listResponse = new CategoryListResponse();
        for (Category c:categoryList){
            CategoryResponseDTO responseDTO= new CategoryResponseDTO();
            responseDTO.setCategoryName(c.getCategoryName());
            responseDTO.setId(c.getId());
            listResponse.getCategoryResponseDTOS().add(responseDTO);
        }
        return listResponse;

    }

    public static CategoryResponseDTO convertCategoryToCategoryResponse(Category c){
        CategoryResponseDTO categoryResponseDTO= new CategoryResponseDTO();
        categoryResponseDTO.setId(c.getId());
        categoryResponseDTO.setCategoryName(c.getCategoryName());
        return categoryResponseDTO;
    }

    public static  Category convertCategoryRequestDTOtoCategory (CategoryRequestDTO requestDTO){
        Category c = new Category();
        c.setCategoryName(requestDTO.getCategoryName());
        c.setCategoryAddedAt(new Date());
        return c;
    }
}
