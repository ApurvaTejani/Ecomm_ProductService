package com.capstone.ecomm_product.Repositories;

import com.capstone.ecomm_product.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}