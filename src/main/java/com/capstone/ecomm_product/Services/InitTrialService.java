package com.capstone.ecomm_product.Services;


import com.capstone.ecomm_product.Models.Category;
import com.capstone.ecomm_product.Models.Order;
import com.capstone.ecomm_product.Models.Price;
import com.capstone.ecomm_product.Models.Product;
import com.capstone.ecomm_product.Repositories.CategoryRepository;
import com.capstone.ecomm_product.Repositories.OrderRepository;
import com.capstone.ecomm_product.Repositories.PriceRepository;
import com.capstone.ecomm_product.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class InitTrialService {


    private CategoryRepository cr;

    private OrderRepository or;

    private PriceRepository pr;

    private ProductRepository prodr;

    public InitTrialService(CategoryRepository cr, OrderRepository or, PriceRepository pr, ProductRepository prodr) {
        this.cr = cr;
        this.or = or;
        this.pr = pr;
        this.prodr = prodr;
    }

    public void initiate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Category Name");
        String categoryName=sc.nextLine();
        Category c = new Category();
        c.setCategory(categoryName);
        cr.save(c);
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
            pr.save(p1);
            sc.nextLine();
            System.out.println("DO you want to exit ?");
            String val=sc.nextLine();
            p=prodr.save(p);
            if(val.equalsIgnoreCase("yes"))
                break;

        }
        Order order = new Order();
        while (true){

            String title =sc.nextLine();
            Optional<Product> productOptional = prodr.findByTitle(title);
            List<Product> productList= new ArrayList<>();
            productList.add(productOptional.get());
            System.out.println("Do you want to Exit?");
            String val=sc.nextLine();
            if(val.equalsIgnoreCase("yes")) {
                order.setProductList(productList);
                break;
            }
        }
        or.save(order);



    }
}
