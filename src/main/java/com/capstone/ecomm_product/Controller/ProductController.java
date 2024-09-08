package com.capstone.ecomm_product.Controller;

import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.ProductNotFoundException;
import com.capstone.ecomm_product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {

    /*
    Domain Name -> IP+Port -> OS in server -> port is binded to process -> Tomcat is binded to 8080
    HTTP -> Tomcat -> DispatcherServlet[loads all URls and handler mappings] -> Servlet Container -> Servlets
 */



    private ProductService ps;

    @Autowired
    public ProductController(@Qualifier("productServiceImpl") ProductService ps) {
        this.ps = ps;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id")String productId) throws ProductNotFoundException {
        UUID uuid= UUID.fromString(productId);
        ProductResponseDTO responseDTO=ps.getProductById(uuid);
       return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        ProductListResponse productListResponse=ps.getAllProducts();
        return ResponseEntity.ok(productListResponse);
    }

    @PostMapping("/products")
    public ResponseEntity createProucts(@RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO responseDTO=ps.createProduct(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id")String productId) throws ProductNotFoundException {
        UUID uuid= UUID.fromString(productId);
        boolean responseStatus=ps.deleteProduct(uuid);
        return ResponseEntity.ok(responseStatus);
    }


}

