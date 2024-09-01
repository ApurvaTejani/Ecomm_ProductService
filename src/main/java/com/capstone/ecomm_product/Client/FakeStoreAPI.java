package com.capstone.ecomm_product.Client;

import com.capstone.ecomm_product.DTOs.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.capstone.ecomm_product.Mapper.ProductMapper.productRequestToFakeStoreProductRequest;

@Component
public class FakeStoreAPI {
    private RestTemplateBuilder rtb;

    public FakeStoreAPI(RestTemplateBuilder rtb) {
        this.rtb = rtb;
    }


    public FakeStoreProductResponseDTO createProductCallToAPI(ProductRequestDTO productRequestDTO){
        String url="https://fakestoreapi.com/products";
        RestTemplate restTemplate=rtb.build();
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO=productRequestToFakeStoreProductRequest(productRequestDTO);
        ResponseEntity<FakeStoreProductResponseDTO> dtoResponseEntity=restTemplate.postForEntity(url,fakeStoreProductRequestDTO,FakeStoreProductResponseDTO.class);
        return dtoResponseEntity.getBody();
    }

    public FakeStoreProductResponseDTO getProductById(int id){
        String url="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate= rtb.build();
        ResponseEntity<FakeStoreProductResponseDTO> response=restTemplate.getForEntity(url,FakeStoreProductResponseDTO.class);
        return response.getBody();
    }


    public List<FakeStoreProductResponseDTO> getAllProducts(){
        String url="https://fakestoreapi.com/products";
        RestTemplate restTemplate=rtb.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> fakeStoreProductsArray=restTemplate.getForEntity(url,FakeStoreProductResponseDTO[].class);
        return List.of(fakeStoreProductsArray.getBody());
    }

    public void deleteProduct(int id){
        String url="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate=rtb.build();
        restTemplate.delete(url);
    }
}
