package com.capstone.ecomm_product.Repositories;

import com.capstone.ecomm_product.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByTitle(String title);

    Optional<Product> findById(UUID id);


    List<Product> findAllByCategoryId(UUID uuids);



}