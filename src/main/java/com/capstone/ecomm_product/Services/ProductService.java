package com.capstone.ecomm_product.Services;

import com.capstone.ecomm_product.Exception.BadRequestClient;
import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;


import java.util.UUID;

public interface ProductService {


     ProductListResponse getAllProducts();
     ProductResponseDTO getProductById(UUID id) throws ResourceNotFoundException;
     ProductResponseDTO createProduct(ProductRequestDTO requestDTO) throws ResourceNotFoundException;
     boolean deleteProduct(UUID id) throws ResourceNotFoundException;
     ProductResponseDTO updateProduct(UUID id,ProductRequestDTO updateProduct) throws BadRequestClient;

}
