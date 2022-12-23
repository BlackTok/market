package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.entities.Product;
import ru.gb.spring.market.entities.PurchaseProduct;
import ru.gb.spring.market.repositories.PurchaseProductDao;

@Service
@RequiredArgsConstructor
public class PurchaseProductService {
    private final PurchaseProductDao purchaseProductDao;

    public void saveProduct(PurchaseProduct product) {
        purchaseProductDao.save(product);
    }

    public void flush() {
        purchaseProductDao.flush();
    }
}
