package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.market.dto.Cart;
import ru.gb.spring.market.dto.CartItem;
import ru.gb.spring.market.dto.ProductDto;
import ru.gb.spring.market.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCart();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        cartService.addProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }
}
