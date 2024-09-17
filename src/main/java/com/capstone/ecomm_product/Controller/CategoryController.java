package com.capstone.ecomm_product.Controller;


import com.capstone.ecomm_product.DTOs.*;
import com.capstone.ecomm_product.Exception.*;

import com.capstone.ecomm_product.Services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatConversionException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService cs;

    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryListResponse> getAllCategories(){
   CategoryListResponse categoryListResponse=cs.getAllCategories();
   return ResponseEntity.ok(categoryListResponse );
    }


    @GetMapping("/public/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") UUID id) {

            CategoryResponseDTO categoryResponseDTO = cs.getCategoryById(id);
            return ResponseEntity.ok(categoryResponseDTO);

    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable("id") UUID id){
        cs.deleteCategoryById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryById(@PathVariable("id") UUID id,@RequestBody CategoryRequestDTO requestDTO)  {

            CategoryResponseDTO responseDTO = cs.updateCategoryById(id, requestDTO);
            return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) throws BadRequestClient {
        try {
            CategoryResponseDTO responseDTO = cs.createProduct(requestDTO);
            return ResponseEntity.ok(responseDTO);
        }
        catch (TransactionSystemException e){
            throw new BadRequestClient("Category","Category Name",requestDTO.getCategoryName());
        }

    }

@GetMapping("/public/categories/{id}/products")
        public ResponseEntity<List<ProductResponseDTO>> findAllProductsUnderCategory(@PathVariable("id") UUID id) {
            ProductListResponse productResponseDTOList=cs.findAllProductsUnderCategory(id);
            return ResponseEntity.ok(productResponseDTOList.getResponseDTOList());
        }
    }

