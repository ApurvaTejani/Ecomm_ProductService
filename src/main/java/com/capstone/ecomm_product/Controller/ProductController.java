package com.capstone.ecomm_product.Controller;

import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    /*
    Domain Name -> IP+Port -> OS in server -> port is binded to process -> Tomcat is binded to 8080
    HTTP -> Tomcat -> DispatcherServlet[loads all URls and handler mappings] -> Servlet Container -> Servlets
 */

    @Autowired
    @Qualifier("fakeStoreProductService")
    private ProductService ps;

    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable("id")int productId){
        ProductResponseDTO responseDTO=ps.getProductById(productId);
       return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        ProductListResponse productListResponse=ps.getAllProducts();
        return ResponseEntity.ok(productListResponse);
    }
}
