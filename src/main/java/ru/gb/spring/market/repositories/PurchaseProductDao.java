package ru.gb.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.market.entities.PurchaseProduct;

public interface PurchaseProductDao extends JpaRepository<PurchaseProduct, Long> {
}
