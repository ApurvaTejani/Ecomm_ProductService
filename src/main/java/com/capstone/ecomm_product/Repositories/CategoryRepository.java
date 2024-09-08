package com.capstone.ecomm_product.Repositories;

import com.capstone.ecomm_product.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByCategoryName(String categoryName);
}