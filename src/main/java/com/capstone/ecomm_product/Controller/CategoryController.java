package com.capstone.ecomm_product.Controller;


import com.capstone.ecomm_product.Config.AppConstants;
import com.capstone.ecomm_product.DTOs.*;
import com.capstone.ecomm_product.Exception.*;

import com.capstone.ecomm_product.Services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/api")
public class CategoryController {

    private CategoryService cs;

    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryListResponse> getAllCategories(@RequestParam(name="pageNo", required = false,defaultValue=AppConstants.PAGE_NO_DEFAULT) Integer pageNo,
                                                                 @RequestParam (name="pageSize",required = false,defaultValue = AppConstants.PAGE_SIZE_DEFAULT) Integer pageSize,
                                                                 @RequestParam(name="sortBy",required = false,defaultValue = AppConstants.sortBy) String sortBy,
                                                                 @RequestParam(name = "sortOrder",required = false,defaultValue = AppConstants.sortOrder) String sortOrder){
        log.info("Attempting to get all category details");
        CategoryListResponse categoryListResponse=cs.getAllCategories(pageNo,pageSize,sortBy,sortOrder);
        log.info("Category Details retrieved successfully");
   return ResponseEntity.ok(categoryListResponse );
    }


    @GetMapping("/public/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") UUID id) {
            log.info("Attempting to get category details for id {} clicked",id);
            CategoryResponseDTO categoryResponseDTO = cs.getCategoryById(id);
            log.info("Successfully retrieved category with id {}",id);
            return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable("id") UUID id){
        log.info("Attempting to delete a product with id: {}", id);
        cs.deleteCategoryById(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryById(@PathVariable("id") UUID id,@Valid @RequestBody CategoryRequestDTO requestDTO)  {
            log.info("Attempting to edit  category with id: {}", id);
            CategoryResponseDTO responseDTO = cs.updateCategoryById(id, requestDTO);
            return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) throws BadRequestClient {

            log.info("Attempting to create a new category with data: {}", requestDTO);
            CategoryResponseDTO responseDTO = cs.createProduct(requestDTO);
            log.info("Category created successfully with ID: {}", responseDTO.getId());
            return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/public/categories/id/{id}/products")
    public ResponseEntity<List<ProductResponseDTO>> findAllProductsUnderCategory(@PathVariable("id") UUID id) {
        log.info("Attempting to get Products details for category id {} clicked",id);
        ProductListResponse productResponseDTOList = cs.findAllProductsUnderCategory(id);
        return ResponseEntity.ok(productResponseDTOList.getResponseDTOList());
    }


    @GetMapping("/public/categories/{name}/products")
    public ResponseEntity<List<ProductResponseDTO>> findAllProductsUnderCategoryName(@PathVariable("name") String name){
        log.info("Attempting to get Products details for category name {} clicked",name);
        ProductListResponse productListResponse=cs.findAllProductsUnderCategoryName(name);
        return ResponseEntity.ok(productListResponse.getResponseDTOList());
        }
     }

