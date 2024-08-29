package com.capstone.ecomm_product.Services;

import Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;

import java.util.List;

public interface ProductService {


     ProductListResponse getAllProducts();
     ProductResponseDTO getProductById(int id);
     Product createProduct(Product product);
     Product deleteProduct(int id);
     Product updateProduct(int i,Product updateProduct);

}
