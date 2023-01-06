package ru.gb.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring.market.entities.Warehouse;

public interface WarehouseDao extends JpaRepository<Warehouse, Long> {
}
