package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.entities.Warehouse;
import ru.gb.spring.market.repositories.WarehouseDao;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseDao warehouseDao;

    public void changeAmount(Warehouse warehouse) {
        warehouseDao.saveAndFlush(warehouse);
    }
}
