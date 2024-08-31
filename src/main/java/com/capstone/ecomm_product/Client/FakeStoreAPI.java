package com.capstone.ecomm_product.Client;

import com.capstone.ecomm_product.DTOs.FakeStoreProductRequestDTO;
import com.capstone.ecomm_product.DTOs.FakeStoreProductResponseDTO;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static com.capstone.ecomm_product.Mapper.ProductMapper.productRequestToFakeStoreProductRequest;
import static com.capstone.ecomm_product.Mapper.ProductMapper.fakeStoreResponseToProductResponse;

@Component
public class FakeStoreAPI {
    private RestTemplateBuilder rtb;

    public FakeStoreAPI(RestTemplateBuilder rtb) {
        this.rtb = rtb;
    }


    public ProductResponseDTO createProductCallToAPI(ProductRequestDTO productRequestDTO){
        String url="https://fakestoreapi.com/products";
        RestTemplate restTemplate=rtb.build();
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO=productRequestToFakeStoreProductRequest(productRequestDTO);
        ResponseEntity<FakeStoreProductResponseDTO> dtoResponseEntity=restTemplate.postForEntity(url,fakeStoreProductRequestDTO,FakeStoreProductResponseDTO.class);
        ProductResponseDTO productResponseDTO=fakeStoreResponseToProductResponse(dtoResponseEntity.getBody());
        return productResponseDTO;
    }
}
