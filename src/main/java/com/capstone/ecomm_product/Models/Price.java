package com.capstone.ecomm_product.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Price extends BaseClass{
    private String currency;
    private double amount;
    private double discount;
}
