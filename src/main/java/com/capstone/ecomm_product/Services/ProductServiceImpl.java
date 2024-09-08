package com.capstone.ecomm_product.Services;


import com.capstone.ecomm_product.DTOs.ProductListResponse;
import com.capstone.ecomm_product.DTOs.ProductRequestDTO;
import com.capstone.ecomm_product.DTOs.ProductResponseDTO;
import com.capstone.ecomm_product.Exception.ProductNotFoundException;
import com.capstone.ecomm_product.Models.Category;
import com.capstone.ecomm_product.Models.Order;
import com.capstone.ecomm_product.Models.Price;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Repositories.CategoryRepository;
import com.capstone.ecomm_product.Repositories.OrderRepository;
import com.capstone.ecomm_product.Repositories.PriceRepository;
import com.capstone.ecomm_product.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.capstone.ecomm_product.Mapper.ProductMapper.convertProductListToProductListResponse;
import static com.capstone.ecomm_product.Mapper.ProductMapper.convertProductToProductResponse;

@Service
public class ProductServiceImpl implements ProductService{


    private CategoryRepository cr;

    private OrderRepository or;

    private PriceRepository pr;

    private ProductRepository prodr;

    public ProductServiceImpl(CategoryRepository cr, OrderRepository or, PriceRepository pr, ProductRepository prodr) {
        this.cr = cr;
        this.or = or;
        this.pr = pr;
        this.prodr = prodr;
    }

    public void initiate() throws ProductNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Category Name");
        String categoryName=sc.nextLine();
        Optional<Category>categoryOptional=cr.findByCategoryName(categoryName);
        Category c;
        if(categoryOptional.isEmpty()) {
             c = new Category();
            c.setCategoryName(categoryName);
            c = cr.save(c);
        }
        else {
             c =categoryOptional.get();
        }
        while(true) {

            Product p = new Product();
            p.setCategory(c);
            p.setImage("http://someImage.com");
            System.out.println("Enter the product Name for category " + categoryName);
            String title = sc.nextLine();
            p.setTitle(title);
            System.out.println("Enter the Description of Product " + title);
            String description=sc.nextLine();
            p.setDescription(description);
            System.out.println("Enter the price of product " + title);
            Price p1 = new Price();
            p1.setCurrency("INR");
            double amount = sc.nextDouble();
            p1.setAmount(amount);
            p1.setDiscount(0.00);
            p1=pr.save(p1);
            p.setPrice(p1);
            sc.nextLine();
            System.out.println("DO you want to exit ?");
            String val=sc.nextLine();
            p=prodr.save(p);
            if(val.equalsIgnoreCase("yes"))
                break;

        }
        Order order = new Order();
        List<Product> productList= new ArrayList<>();
        while (true){
            System.out.println("Enetr the products which you want to add in cart? ");
            String title =sc.nextLine();
            Optional<Product> productOptional = prodr.findByTitle(title);
            if (productOptional.isEmpty()){
                throw new ProductNotFoundException();
            }
            productList.add(productOptional.get());
            System.out.println("Do you want to Exit Card?");
            String val=sc.nextLine();
            if(val.equalsIgnoreCase("yes")) {
                order.setProductList(productList);
                break;
            }
        }
        or.save(order);



    }

    @Override
    public ProductListResponse getAllProducts() {
        List<Product> products = prodr.findAll();
        ProductListResponse productListResponse=convertProductListToProductListResponse(products);
        return productListResponse;
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) throws ProductNotFoundException {

        Optional<Product> productOptional=prodr.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException();
        }
        ProductResponseDTO responseDTO= convertProductToProductResponse(productOptional.get());

        return responseDTO;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product p = new Product();
        Price p1 = new Price();
        p1.setAmount(requestDTO.getPrice());
        p1.setDiscount(0.00);
        p1.setCurrency(requestDTO.getCurrency());
        p1=pr.save(p1);
        p.setPrice(p1);
        Category c;
        Optional<Category> categoryOptional =cr.findByCategoryName(requestDTO.getCategory());
        if(categoryOptional.isEmpty()) {
             c = new Category();
            c.setCategoryName(requestDTO.getCategory());
            c.setCategoryAddedAt(new Date());
            c=cr.save(c);
        }
        else {
            c=categoryOptional.get();
        }
        p.setCategory(c);
        p.setImage(requestDTO.getImage());
        p.setDescription(requestDTO.getDescription());
        p.setTitle(requestDTO.getTitle());
        p.setProductAddedAt(new Date());
        p=prodr.save(p);

return convertProductToProductResponse(p);
    }

    @Override
    public boolean deleteProduct(UUID id) throws ProductNotFoundException {
        try {
            prodr.deleteById(id);
        } catch (Exception e){
            throw new ProductNotFoundException();
        }
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        return null;
    }
}
