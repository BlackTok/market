package ru.gb.spring.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.spring.market.dto.CustomerDto;
import ru.gb.spring.market.entities.Customer;
import ru.gb.spring.market.entities.Role;
import ru.gb.spring.market.repositories.CustomerDao;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerConverter {
    private final PurchaseConverter purchaseConverter;

    public CustomerDto EntityToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
                customer.getRoles(),
                customer.getPurchases().stream().map(purchaseConverter::EntityToDto).collect(Collectors.toList())
        );
    }
}
