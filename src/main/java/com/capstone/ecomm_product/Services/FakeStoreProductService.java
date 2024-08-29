package com.capstone.ecomm_product.Services;

import Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder rtb;

    public FakeStoreProductService(RestTemplateBuilder rtb) {
        this.rtb = rtb;
    }

    @Override
    public ProductListResponse getAllProducts() {
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String url="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate= rtb.build();
        ResponseEntity<ProductResponseDTO> response=restTemplate.getForEntity(url,ProductResponseDTO.class);
        return response.getBody();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        return null;
    }

    @Override
    public Product updateProduct(int i, Product updateProduct) {
        return null;
    }
}
