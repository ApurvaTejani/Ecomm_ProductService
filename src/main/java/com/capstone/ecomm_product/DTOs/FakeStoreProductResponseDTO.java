package com.capstone.ecomm_product.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    private int id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
