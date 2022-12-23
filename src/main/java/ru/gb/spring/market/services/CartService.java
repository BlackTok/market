package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.converters.CartItemConverter;
import ru.gb.spring.market.dto.Cart;
import ru.gb.spring.market.dto.CartItem;
import ru.gb.spring.market.dto.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final CartItemConverter cartItemConverter;

    public List<CartItem> getCart() {
        return cart.getCart();
    }

    public void addProduct(ProductDto productDto) {
        CartItem cartItem = cartItemConverter.productDtoToCartItem(productDto);
        cart.add(cartItem);
    }

    public void deleteProduct(Long id) {
        cart.delete(id);
    }

    public void clearCart() {
        cart.clearCart();
    }
}
