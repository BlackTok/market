package ru.gb.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.market.entities.Purchase;

@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Long> {
}
