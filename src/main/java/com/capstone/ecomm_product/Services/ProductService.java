package com.capstone.ecomm_product.Services;

import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.ProductNotFoundException;

import java.util.UUID;

public interface ProductService {


     ProductListResponse getAllProducts();
     ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException;
     ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
     boolean deleteProduct(UUID id) throws ProductNotFoundException;
     Product updateProduct(int id,Product updateProduct);

}
