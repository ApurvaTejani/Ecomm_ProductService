package com.capstone.ecomm_product.Services;

import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Client.FakeStoreAPI;
import com.capstone.ecomm_product.DTOs.FakeStoreProductResponseDTO;
import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import static  com.capstone.ecomm_product.utils.NullChecks.isNull;

import com.capstone.ecomm_product.Exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.capstone.ecomm_product.Mapper.ProductMapper.fakeStoreResponseToProductResponse;

@Service
public class FakeStoreProductService implements ProductService{


    private FakeStoreAPI apiCall;

    public FakeStoreProductService( FakeStoreAPI apiCall) {

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
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO=apiCall.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO))
        {
            throw new ProductNotFoundException("Product Not Found with id :"+id);
        }
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
