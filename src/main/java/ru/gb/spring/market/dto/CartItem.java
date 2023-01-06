package ru.gb.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String title;
    private String unit;
    private double amount;
    private double price;
    private double totalPrice;
}
