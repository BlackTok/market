package ru.gb.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseProductDto {
    private ProductDto productDto;
    private Double count;
    private Double price;
    private Double sum;
}
