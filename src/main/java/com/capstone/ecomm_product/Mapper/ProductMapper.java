package com.capstone.ecomm_product.Mapper;

import com.capstone.ecomm_product.DTOs.*;
import com.capstone.ecomm_product.Models.Product;

import java.util.List;
import java.util.UUID;

public class ProductMapper {


    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest (ProductRequestDTO productRequestDTO){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeStoreResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO){
        ProductResponseDTO productResponseDTO= new ProductResponseDTO();
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
//        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        return productResponseDTO;
    }

    public static ProductListResponse convertProductListToProductListResponse(List<Product> products){
        ProductListResponse productListResponse = new ProductListResponse();
        for (Product p: products){
            ProductResponseDTO responseDTO = new ProductResponseDTO();
            responseDTO.setId(p.getId());
            responseDTO.setDescription(p.getDescription());
            responseDTO.setTitle(p.getTitle());
            responseDTO.setCategory(p.getCategory().getCategoryName());
            responseDTO.setImage(p.getImage());
            responseDTO.setPrice(p.getPrice().getAmount());
            responseDTO.setCurrency(p.getPrice().getCurrency());
            productListResponse.getResponseDTOList().add(responseDTO);
        }
        return productListResponse;

    }

    public static ProductResponseDTO convertProductToProductResponse(Product p){
        ProductResponseDTO responseDTO= new ProductResponseDTO();
        responseDTO.setId(p.getId());
        responseDTO.setPrice(p.getPrice().getAmount());
        responseDTO.setCurrency(p.getPrice().getCurrency());
        responseDTO.setTitle(p.getTitle());
        responseDTO.setCategory(p.getCategory().getCategoryName());
        responseDTO.setImage(p.getImage());
        responseDTO.setDescription(p.getDescription());
        return responseDTO;
    }
    public static Product convertProductRequestToProduct(UUID id,ProductRequestDTO requestDTO){

        return null;
    }
}
