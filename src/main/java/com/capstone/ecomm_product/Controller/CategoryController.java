package com.capstone.ecomm_product.Controller;


import com.capstone.ecomm_product.DTOs.*;
import com.capstone.ecomm_product.Exception.CategoryNotFoundException;
import com.capstone.ecomm_product.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService cs;

    public CategoryController(CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryListResponse> getAllCategories(){
   CategoryListResponse categoryListResponse=cs.getAllCategories();
   return ResponseEntity.ok(categoryListResponse );
    }


    @GetMapping("/public/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") UUID id) throws CategoryNotFoundException {
        CategoryResponseDTO categoryResponseDTO=cs.getCategoryById(id);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable("id") UUID id){
        cs.deleteCategoryById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryById(@PathVariable("id") UUID id,@RequestBody CategoryRequestDTO requestDTO) throws CategoryNotFoundException {
        CategoryResponseDTO responseDTO=cs.updateCategoryById(id,requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO responseDTO = cs.createProduct(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

@GetMapping("/public/categories/{id}/products")
        public ResponseEntity<List<ProductResponseDTO>> findAllProductsUnderCategory(@PathVariable("id") UUID id) {
            ProductListResponse productResponseDTOList=cs.findAllProductsUnderCategory(id);
            return ResponseEntity.ok(productResponseDTOList.getResponseDTOList());
        }
    }

