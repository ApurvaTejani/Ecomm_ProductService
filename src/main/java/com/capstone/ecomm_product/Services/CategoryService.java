package com.capstone.ecomm_product.Services;

import com.capstone.ecomm_product.DTOs.*;
import com.capstone.ecomm_product.Exception.BadRequestClient;
import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public interface CategoryService {

     CategoryListResponse getAllCategories();

     CategoryResponseDTO getCategoryById(UUID id) throws ResourceNotFoundException;

     void deleteCategoryById(UUID id);

     CategoryResponseDTO updateCategoryById(UUID id, CategoryRequestDTO requestDTO);

     CategoryResponseDTO createProduct(CategoryRequestDTO requestDTO) throws BadRequestClient;

     ProductListResponse findAllProductsUnderCategory(UUID id);
}
