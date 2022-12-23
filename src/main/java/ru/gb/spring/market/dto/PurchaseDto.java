package ru.gb.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private LocalDateTime createdAt;
    private List<PurchaseProductDto> products;
}
