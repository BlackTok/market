package ru.gb.spring.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.spring.market.dto.PurchaseProductDto;
import ru.gb.spring.market.entities.PurchaseProduct;

@Component
@RequiredArgsConstructor
public class PurchaseProductConverter {
    private final ProductConverter productConverter;

    public PurchaseProductDto EntityToDto(PurchaseProduct purchaseProduct) {
        return new PurchaseProductDto(
                //purchaseProduct.getId(),
                productConverter.EntityRoDto(purchaseProduct.getProduct()),
                purchaseProduct.getProductCount(),
                purchaseProduct.getProductPrice(),
                purchaseProduct.getProductCount() * purchaseProduct.getProductPrice()
        );
    }
}
