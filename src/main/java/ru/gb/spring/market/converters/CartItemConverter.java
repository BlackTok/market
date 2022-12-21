package ru.gb.spring.market.converters;

import ru.gb.spring.market.dto.CartItem;
import ru.gb.spring.market.dto.ProductDto;

public class CartItemConverter {
    public CartItem productDtoToCartItem(ProductDto productDto) {
        return new CartItem(
                productDto.getId(),
                productDto.getName(),
                productDto.getUnit(),
                1,
                productDto.getPrice(),
                productDto.getPrice()
        );
    }
}
