package com.capstone.ecomm_product.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseClass{

    private String title;
    @OneToOne
    private Price price;
    private String description;
    private String image;

    @ManyToOne
    private Category category;


}
