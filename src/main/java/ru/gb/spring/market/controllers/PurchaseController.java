package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.market.services.PurchaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    public void createPurchase() {
        purchaseService.createPurchase();
    }
}
