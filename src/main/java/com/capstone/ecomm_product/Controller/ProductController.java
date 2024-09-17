package com.capstone.ecomm_product.Controller;

import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.BadRequestClient;

import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api")
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

    @GetMapping("/public/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id")String productId) throws ResourceNotFoundException {
        UUID uuid= UUID.fromString(productId);
        log.info("Attempting to get product details for id {} clicked",productId);
        ProductResponseDTO responseDTO=ps.getProductById(uuid);
        log.info("Successfully retrieved product with id {}",productId);
       return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/public/products")
    public ResponseEntity getAllProducts(){
        log.info("Attempting to get all product details");
        ProductListResponse productListResponse=ps.getAllProducts();
        log.info("Product Details retrieved successfully");
        return ResponseEntity.ok(productListResponse);
    }

    @PostMapping("/admin/products")
    public ResponseEntity createProucts(@RequestBody ProductRequestDTO requestDTO) throws BadRequestClient,ResourceNotFoundException {
        if(requestDTO==null){
            log.error("Product creation failed: ProductRequestDTO is null or missing required attributes");
            throw new BadRequestClient("Product","Product ID","with single or multiple attribute");
        }
        log.info("Attempting to create a new product with data: {}", requestDTO);
        ProductResponseDTO responseDTO=ps.createProduct(requestDTO);
        log.info("Product created successfully with ID: {}", responseDTO.getId());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id")String productId)  {
        UUID uuid= UUID.fromString(productId);
        log.info("Attempting to delete a product with id: {}", productId);
        boolean responseStatus=ps.deleteProduct(uuid);
        return ResponseEntity.ok(responseStatus);
    }


    @PutMapping("/admin/products/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") UUID id,@RequestBody ProductRequestDTO requestDTO) throws BadRequestClient{
        log.info("Attempting to edit  product with id: {}", id);
        ProductResponseDTO responseDTO=ps.updateProduct(id,requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}

