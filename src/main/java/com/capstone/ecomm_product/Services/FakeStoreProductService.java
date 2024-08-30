package com.capstone.ecomm_product.Services;

import Models.Product;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
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
        String url="https://fakestoreapi.com/products";
         RestTemplate restTemplate=rtb.build();
         ResponseEntity<ProductResponseDTO[]> productResponseArray=restTemplate.getForEntity(url,ProductResponseDTO[].class);
         ProductListResponse productList= new ProductListResponse();
         for (ProductResponseDTO responseDTO:productResponseArray.getBody()){
             productList.getResponseDTOList().add(responseDTO);
         }
         return productList;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String url="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate= rtb.build();
        ResponseEntity<ProductResponseDTO> response=restTemplate.getForEntity(url,ProductResponseDTO.class);
        return response.getBody();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        String url="https://fakestoreapi.com/products";
        RestTemplate restTemplate=rtb.build();
        ResponseEntity<ProductResponseDTO> dtoResponseEntity=restTemplate.postForEntity(url,requestDTO,ProductResponseDTO.class);
        return dtoResponseEntity.getBody();

    }

    @Override
    public boolean deleteProduct(int id) {
        String url="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate=rtb.build();
        restTemplate.delete(url);
        return true;
    }

    @Override
    public Product updateProduct(int i, Product updateProduct) {
        return null;
    }
}
