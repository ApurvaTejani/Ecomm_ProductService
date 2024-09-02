package com.capstone.ecomm_product.Models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseClass {
    private String category;
}
