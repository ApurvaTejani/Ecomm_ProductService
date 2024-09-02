package com.capstone.ecomm_product.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseClass{
    private String title;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String image;
    private String email;
    private String password;
}
