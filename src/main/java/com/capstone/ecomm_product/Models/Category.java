package com.capstone.ecomm_product.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseClass {
    private String category;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
