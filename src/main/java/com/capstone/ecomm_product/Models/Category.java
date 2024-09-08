package com.capstone.ecomm_product.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseClass {
    private String categoryName;

    private Date categoryAddedAt;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
