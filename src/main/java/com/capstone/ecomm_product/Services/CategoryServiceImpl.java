package com.capstone.ecomm_product.Services;

import com.capstone.ecomm_product.DTOs.*;

import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Models.Category;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Repositories.CategoryRepository;
import com.capstone.ecomm_product.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.capstone.ecomm_product.Mapper.CategoryMapper.*;
import static com.capstone.ecomm_product.Mapper.ProductMapper.*;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository cr;
    private ProductRepository pr;

    public CategoryServiceImpl(CategoryRepository cr,ProductRepository pr) {
        this.cr = cr;
        this.pr=pr;
    }

    @Override
    public CategoryListResponse getAllCategories() {
        List<Category> categoryList=cr.findAll();
        CategoryListResponse categoryListResponse = convertCategoryListToCategoryListResponseDTO(categoryList);
        return categoryListResponse;
    }

    @Override
    public CategoryResponseDTO getCategoryById(UUID id) throws ResourceNotFoundException {
        Optional<Category> categoryOptional=cr.findById(id);
        if(categoryOptional.isPresent()){
          CategoryResponseDTO responseDTO=  convertCategoryToCategoryResponse(categoryOptional.get());
          return responseDTO;
        }
        else {
            throw new ResourceNotFoundException("Category","categoryId",id);
        }

    }

    @Override
    public void deleteCategoryById(UUID id) {
        Optional<Category> category=cr.findById(id);
        if(category.isPresent()) {
            cr.deleteById(id);
        }
        else {
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
            return convertCategoryToCategoryResponse(category);
        }
        else {
            throw new ResourceNotFoundException("Category","categoryId",id);
        }
    }

    @Override
    public CategoryResponseDTO createProduct(CategoryRequestDTO requestDTO) {

        Category category= convertCategoryRequestDTOtoCategory(requestDTO);
        category=cr.save(category);
        return convertCategoryToCategoryResponse(category);
    }

    @Override
    public ProductListResponse findAllProductsUnderCategory(UUID id) {
        List<Product> productList =pr.findAllByCategoryId(id);
        if(productList.isEmpty()){
            throw new ResourceNotFoundException("Category","Category Id","does not have any product associated to it or Id itself does not exists in DB");
        }
        return convertProductListToProductListResponse(productList);
    }
}
