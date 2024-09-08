package com.capstone.ecomm_product;

import com.capstone.ecomm_product.Services.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommProductApplication implements CommandLineRunner {

    private ProductServiceImpl trialService;

    public EcommProductApplication(ProductServiceImpl trialService) {
        this.trialService = trialService;
    }

    public static void main(String[] args) {

        SpringApplication.run(EcommProductApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trialService.initiate();
    }
}
