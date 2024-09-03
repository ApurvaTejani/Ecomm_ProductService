package com.capstone.ecomm_product.Repositories;

import com.capstone.ecomm_product.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}