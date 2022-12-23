package ru.gb.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.converters.ProductConverter;
import ru.gb.spring.market.dto.ProductDto;
import ru.gb.spring.market.entities.Product;
import ru.gb.spring.market.repositories.ProductDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductConverter productConverter;

    public List<ProductDto> findAll() {
        return productDao.findAll().stream().map(productConverter::EntityRoDto).collect(Collectors.toList());
    }

    public List<Product> findIfEqualIds(List<Long> idList) {
        return productDao.findAllById(idList);
    }

    public Optional<Product> findById(Long id) {
        return productDao.findById(id);
    }
}
