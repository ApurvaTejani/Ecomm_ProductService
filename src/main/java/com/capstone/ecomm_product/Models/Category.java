package com.capstone.ecomm_product.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseClass {
    @NotBlank
    @Size(min=5,message = "Category Name should be minimum of 5")
    private String categoryName;

    private Date categoryAddedAt;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
