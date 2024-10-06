package com.capstone.ecomm_product.Services;


import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.BadRequestClient;
import com.capstone.ecomm_product.Exception.ResourceNotFoundException;
import com.capstone.ecomm_product.Mapper.ProductMapper;
import com.capstone.ecomm_product.Models.Category;
import com.capstone.ecomm_product.Models.Price;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Repositories.CategoryRepository;
import com.capstone.ecomm_product.Repositories.OrderRepository;
import com.capstone.ecomm_product.Repositories.PriceRepository;
import com.capstone.ecomm_product.Repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.capstone.ecomm_product.Mapper.ProductMapper.convertProductListToProductListResponse;
import static com.capstone.ecomm_product.Mapper.ProductMapper.convertProductToProductResponse;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{


    private CategoryRepository cr;

    private OrderRepository or;

    private PriceRepository pr;

    private ProductRepository prodr;

    @Autowired
    private ModelMapper modelMapper;

    public ProductServiceImpl(CategoryRepository cr, OrderRepository or, PriceRepository pr, ProductRepository prodr) {
        this.cr = cr;
        this.or = or;
        this.pr = pr;
        this.prodr = prodr;
    }


    @Override
    public ProductListResponse getAllProducts(Integer pageNo, Integer pageSize,String sortBy, String sortOrder) {
        Sort sortDetails=sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNo,pageSize,sortDetails);
        Page<Product> productPage=prodr.findAll(pageDetails);
        List<Product> products = productPage.getContent();
//        List<ProductResponseDTO> productListResponse=products.stream()
//                .map(product -> modelMapper.map(product,ProductResponseDTO.class)).toList();
        List<ProductResponseDTO> productListResponse = products.stream()
                .map(ProductMapper::convertProductToProductResponse).toList();
        ProductListResponse listResponse= new ProductListResponse();
        listResponse.setResponseDTOList(productListResponse);
        listResponse.setPageNumber(pageNo);
        listResponse.setPageSize(pageSize);
        listResponse.setTotalElements(productPage.getTotalElements());
        listResponse.setTotalPage(productPage.getTotalPages());
        listResponse.setLastPage(productPage.isLast());
        return listResponse;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) throws ResourceNotFoundException {
        log.info("Service finding product with product id {} from DB",id);
        Optional<Product> productOptional=prodr.findById(id);
        if(productOptional.isEmpty()){
            log.error("Product ID with {} does not exists in DB",id);
            throw new ResourceNotFoundException("Product","Product ID",id);
        }
        ProductResponseDTO responseDTO= convertProductToProductResponse(productOptional.get());
        log.info("Product with product id {} found",id);
        return responseDTO;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) throws ResourceNotFoundException {
        Product p = new Product();
        log.info("Starting product creation for product price: {}", requestDTO.getPrice());
        Price p1 = new Price();
        p1.setAmount(requestDTO.getPrice());
        p1.setDiscount(0.00);
        p1.setCurrency(requestDTO.getCurrency());
        log.info("Saving price details: amount={}, currency={})", requestDTO.getPrice(),requestDTO.getCurrency());
        p1=pr.save(p1);
        p.setPrice(p1);
        Category c;
        Optional<Category> categoryOptional =cr.findByCategoryName(requestDTO.getCategory());
        if(categoryOptional.isEmpty()) {
            log.error("Category not found: {}", requestDTO.getCategory());
            throw new ResourceNotFoundException("Category","CategoryName",requestDTO.getCategory());
        }
        else {
            log.info("Category found: {}", requestDTO.getCategory());
            c=categoryOptional.get();
        }
        p.setCategory(c);
        p.setImage(requestDTO.getImage());
        p.setDescription(requestDTO.getDescription());
        p.setTitle(requestDTO.getTitle());
        p.setProductAddedAt(new Date());
        log.info("Saving product: title={}, description={}", p.getTitle(), p.getDescription());
        p=prodr.save(p);
        log.info("Product created successfully with ID: {}", p.getId());
return convertProductToProductResponse(p);
    }

    @Override
    public boolean deleteProduct(UUID id) throws ResourceNotFoundException {
        Optional<Product> productOptional = prodr.findById(id);
        if(productOptional.isEmpty()){
            log.error("Product Not Found with id {}",id);
            throw new ResourceNotFoundException("Delete Product", "Product Id",id);
        }
        else{
           prodr.deleteById(id);
           log.info("Product with product id {} deleted successfully",id);
        }
        return true;
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO updateProduct) throws BadRequestClient {
        Optional<Product> productOptional=prodr.findById(id);
        if(productOptional.isEmpty()){
            log.error("Product Not Found with id {}",id);
            throw new ResourceNotFoundException("Product","Product ID",id);
        }
        else if(!productOptional.get().getCategory().getCategoryName().equals(updateProduct.getCategory()))
        {
            log.error("Category not found: {}", updateProduct.getCategory());
            throw new BadRequestClient("Category", "CategoryName",updateProduct.getCategory());
        }
        else {
          Product product= productOptional.get();
          product.setTitle(updateProduct.getTitle());
          product.setImage(updateProduct.getImage());
          product.setDescription(updateProduct.getDescription());
          Price p = new Price();
          p.setAmount(updateProduct.getPrice());
          product.setPrice(p);
            log.info("Product with product id {} edited successfully",id);
            return convertProductToProductResponse(product);
        }
    }


}
