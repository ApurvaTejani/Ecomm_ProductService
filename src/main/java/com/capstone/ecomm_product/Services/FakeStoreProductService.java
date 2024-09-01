package com.capstone.ecomm_product.Services;

import Models.Product;
import com.capstone.ecomm_product.Client.FakeStoreAPI;
import com.capstone.ecomm_product.DTOs.FakeStoreProductResponseDTO;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.capstone.ecomm_product.Mapper.ProductMapper.fakeStoreResponseToProductResponse;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder rtb;

    private FakeStoreAPI apiCall;

    public FakeStoreProductService(RestTemplateBuilder rtb, FakeStoreAPI apiCall) {
        this.rtb = rtb;
        this.apiCall = apiCall;
    }


    @Override
    public ProductListResponse getAllProducts() {
       List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOList=apiCall.getAllProducts();
      ProductListResponse listResponse= new ProductListResponse();
      for (FakeStoreProductResponseDTO fakeStoreProductResponseDTO:fakeStoreProductResponseDTOList){
          listResponse.getResponseDTOList().add(fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO));
      }
      return listResponse;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO=apiCall.getProductById(id);
        return fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO=apiCall.createProductCallToAPI(requestDTO);
        return fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public boolean deleteProduct(int id) {
        apiCall.deleteProduct(id);
        return true;
    }

    @Override
    public Product updateProduct(int i, Product updateProduct) {
        return null;
    }
}
