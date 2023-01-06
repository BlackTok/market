package ru.gb.spring.market.converters;

import org.springframework.stereotype.Component;
import ru.gb.spring.market.dto.ProductDto;
import ru.gb.spring.market.entities.Product;

@Component
public class ProductConverter {
    public ProductDto EntityRoDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getUnit(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}
