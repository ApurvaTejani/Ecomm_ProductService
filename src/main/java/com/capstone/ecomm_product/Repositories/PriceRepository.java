package com.capstone.ecomm_product.Repositories;

import com.capstone.ecomm_product.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}