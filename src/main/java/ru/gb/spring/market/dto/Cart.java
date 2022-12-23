package ru.gb.spring.market.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.gb.spring.market.converters.CartItemConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {
    private CartItemConverter cartItemConverter;

    private List<CartItem> cartItems;
    private double totalPrice;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.cartItemConverter = new CartItemConverter();
    }

    public List<CartItem> getCart() {
        return Collections.unmodifiableList(cartItems);
    }

    public void add(CartItem newCartItem) {
        if (!cartItems.contains(newCartItem)) {
            cartItems.add(newCartItem);
        } else {
            CartItem cartItem = cartItems.stream().filter(ci -> ci.getId() == newCartItem.getId()).findFirst().get();
            int index = cartItems.indexOf(cartItem);
            cartItem.setAmount(cartItem.getAmount() + 1);
            cartItem.setTotalPrice(cartItem.getTotalPrice() + newCartItem.getTotalPrice());
            cartItems.set(index, cartItem);
        }

        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }
    }

    public void delete(Long id) {
        CartItem cartItem = cartItems.stream()
                .filter(ci -> ci.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Невозможно удалить продукт."));

        cartItems.remove(cartItem);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
