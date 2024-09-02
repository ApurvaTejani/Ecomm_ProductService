package com.capstone.ecomm_product.Client;

import com.capstone.ecomm_product.DTOs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.capstone.ecomm_product.Mapper.ProductMapper.productRequestToFakeStoreProductRequest;

@Component
public class FakeStoreAPI {
    private RestTemplateBuilder rtb;


    private String fakeStoreURL;

    private String productPath;

    public FakeStoreAPI(RestTemplateBuilder rtb,
                        @Value("${fakeStore.URL}") String fakeStoreURL,
                        @Value("${fakeStore.productPath}") String productPath) {
        this.rtb = rtb;
        this.fakeStoreURL=fakeStoreURL;
        this.productPath=productPath;
    }


    public FakeStoreProductResponseDTO createProductCallToAPI(ProductRequestDTO productRequestDTO){
        String createURL=fakeStoreURL+productPath;
        RestTemplate restTemplate=rtb.build();
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO=productRequestToFakeStoreProductRequest(productRequestDTO);
        ResponseEntity<FakeStoreProductResponseDTO> dtoResponseEntity=restTemplate.postForEntity(createURL,fakeStoreProductRequestDTO,FakeStoreProductResponseDTO.class);
        return dtoResponseEntity.getBody();
    }

    public FakeStoreProductResponseDTO getProductById(int id){
        String getProductURL=fakeStoreURL+productPath+"/"+id;
        RestTemplate restTemplate= rtb.build();
        ResponseEntity<FakeStoreProductResponseDTO> response=restTemplate.getForEntity(getProductURL,FakeStoreProductResponseDTO.class);
        return response.getBody();
    }


    public List<FakeStoreProductResponseDTO> getAllProducts(){
        String getAllProductURL=fakeStoreURL+productPath;

        RestTemplate restTemplate=rtb.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> fakeStoreProductsArray=restTemplate.getForEntity(getAllProductURL,FakeStoreProductResponseDTO[].class);
        return List.of(fakeStoreProductsArray.getBody());
    }

    public void deleteProduct(int id){
        String deleteURL=fakeStoreURL+productPath+"/"+id;
        RestTemplate restTemplate=rtb.build();
        restTemplate.delete(deleteURL);
    }
}
