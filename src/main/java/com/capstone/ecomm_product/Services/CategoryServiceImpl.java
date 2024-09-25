package com.capstone.ecomm_product.Services;


import com.capstone.ecomm_product.DTOs.*;

import com.capstone.ecomm_product.Exception.APIException;
import com.capstone.ecomm_product.Exception.BadRequestClient;
import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Models.Category;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Repositories.CategoryRepository;
import com.capstone.ecomm_product.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.capstone.ecomm_product.Mapper.CategoryMapper.*;
import static com.capstone.ecomm_product.Mapper.ProductMapper.*;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository cr;
    private ProductRepository pr;

    @Autowired
   private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository cr,ProductRepository pr) {
        this.cr = cr;
        this.pr=pr;
    }

    @Override
    public CategoryListResponse getAllCategories(Integer pageNo, Integer pageSize,String sortBy,String sortOrder) {
        Sort sortDetails= sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        log.info("Getting Categories Details Page Number : {}, Sorted By: {}, Sorting in {}",pageNo+1,sortBy,sortOrder);
        Pageable pageDetails= PageRequest.of(pageNo,pageSize,sortDetails);
        Page<Category> categoryPage=cr.findAll(pageDetails);
        List<Category> categoryList=categoryPage.getContent();
        List<CategoryResponseDTO> categoryListResponses=categoryList.stream().map(category -> modelMapper.map(category,CategoryResponseDTO.class)).toList();
        CategoryListResponse categoryListResponse= new CategoryListResponse();
        categoryListResponse.setCategoryResponseDTOS(categoryListResponses);
        categoryListResponse.setPageNumber(pageNo);
        categoryListResponse.setPageSize(pageSize);
        categoryListResponse.setTotalPage(categoryPage.getTotalPages());
        categoryListResponse.setTotalElements(categoryPage.getTotalElements());
        categoryListResponse.setLastPage(categoryPage.isLast());
        log.info("Successfully retrieved categories");
//        CategoryListResponse categoryListResponse = convertCategoryListToCategoryListResponseDTO(categoryList);
        return categoryListResponse;
    }

    @Override
    public CategoryResponseDTO getCategoryById(UUID id) throws ResourceNotFoundException {
        log.info("Service finding category with category id {} from DB",id);
        Optional<Category> categoryOptional=cr.findById(id);
        if(categoryOptional.isPresent()){
            CategoryResponseDTO responseDTO1=modelMapper.map(categoryOptional.get(),CategoryResponseDTO.class);
          CategoryResponseDTO responseDTO=  convertCategoryToCategoryResponse(categoryOptional.get());
            log.info("Category with Category id {} found",id);
          return responseDTO1;
        }
        else {
            log.error("Category ID with {} does not exists in DB",id);
            throw new ResourceNotFoundException("Category","categoryId",id);
        }

    }

    @Override
    public void deleteCategoryById(UUID id) {
        Optional<Category> category=cr.findById(id);
        if(category.isPresent()) {
            cr.deleteById(id);
            log.info("Category with category id {} deleted successfully",id);
        }
        else {
            log.error("Category Not Found with id {}",id);
            throw new ResourceNotFoundException("Category","Category ID",id);
        }
    }

    @Override
    public CategoryResponseDTO updateCategoryById(UUID id, CategoryRequestDTO categoryRequestDTO) throws ResourceNotFoundException {
        Optional<Category> categoryOptional=cr.findById(id);
        if(categoryOptional.isPresent()){
            Category c = convertCategoryRequestDTOtoCategory(categoryRequestDTO);
            categoryOptional.get().setCategoryName(c.getCategoryName());
            categoryOptional.get().setCategoryAddedAt(c.getCategoryAddedAt());
            Category category= cr.save(categoryOptional.get());
            log.info("Category with category id {} edited successfully",id);
            return convertCategoryToCategoryResponse(category);
        }
        else {
            log.error("Category Not Found with id {}",id);
            throw new ResourceNotFoundException("Category","categoryId",id);
        }
    }

    @Override
    public CategoryResponseDTO createProduct(CategoryRequestDTO requestDTO) throws BadRequestClient {

        Optional<Category> optionalCategory=cr.findByCategoryName(requestDTO.getCategoryName());
        if (optionalCategory.isPresent()){
            log.error("Category Already Present - {}",requestDTO.getCategoryName());
            throw new APIException("Category Already Present - BAD Request");
        } else if (requestDTO.getCategoryName().isEmpty()) {
            log.error("Category creation failed: CategoryRequestDTO is null or missing required attributes");
            throw new BadRequestClient("Category","Category Name","Empty");
        } else {
            Category category = convertCategoryRequestDTOtoCategory(requestDTO);
            log.info("Starting category creation: {}", category.getCategoryName());
            category = cr.save(category);
            log.info("Category created successfully with ID: {}", category.getId());
            return convertCategoryToCategoryResponse(category);
        }
    }

    @Override
    public ProductListResponse findAllProductsUnderCategory(UUID id) {
        log.info("Service finding products for category id {} from DB",id);
        List<Product> productList =pr.findAllByCategoryId(id);
        if(productList.isEmpty()){
            log.error("Category ID with {} does not exists in DB",id);
            throw new ResourceNotFoundException("Category","Category Id","does not have any product associated to it or Id itself does not exists in DB");
        }
        log.info("Products with Category id {} found",id);
        return convertProductListToProductListResponse(productList);
    }

    @Override
    public ProductListResponse findAllProductsUnderCategoryName(String categoryName) {
        log.info("Service finding products for category name {} from DB",categoryName);
        Optional<Category> categoryOptional=cr.findByCategoryName(categoryName);
        if (categoryOptional.isEmpty()){
            log.error("Category name with {} does not exists in DB",categoryName);
            throw new ResourceNotFoundException("Category","Category Name",categoryName);
        }
        else{
            ProductListResponse productListResponse=findAllProductsUnderCategory(categoryOptional.get().getId());
            log.info("Products with Category name {} found",categoryName);
            return productListResponse;
        }
    }
}
