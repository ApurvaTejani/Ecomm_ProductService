package com.capstone.ecomm_product.Services;

import Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {


     ProductListResponse getAllProducts();
     ProductResponseDTO getProductById(int id) throws ProductNotFoundException;
     ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
     boolean deleteProduct(int id);
     Product updateProduct(int id,Product updateProduct);

}
