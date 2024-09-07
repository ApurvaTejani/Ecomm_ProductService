package com.capstone.ecomm_product.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseClass{

    private String title;
    @OneToOne
    private Price price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
