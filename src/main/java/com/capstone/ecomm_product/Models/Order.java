package com.capstone.ecomm_product.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "ECOMM_ORDER")
public class Order extends BaseClass{
    @ManyToMany
    private List<Product> productList;
}
