package ru.gb.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.gb.spring.market.entities.Role;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String username;
    private Collection<Role> role;
    private List<PurchaseDto> purchases;
}
