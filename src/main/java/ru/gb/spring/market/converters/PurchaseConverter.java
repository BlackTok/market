package ru.gb.spring.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.spring.market.dto.PurchaseDto;
import ru.gb.spring.market.dto.PurchaseProductDto;
import ru.gb.spring.market.entities.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseConverter {
    private final PurchaseProductConverter productConverter;

    public PurchaseDto EntityToDto(Purchase purchase) {
        return new PurchaseDto(
                purchase.getId(),
                purchase.getCreatedAt(),
                purchase.getProducts().stream().map(productConverter::EntityToDto).collect(Collectors.toList())
        );
    }
}
