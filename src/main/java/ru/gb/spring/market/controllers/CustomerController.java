package ru.gb.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.spring.market.dto.CustomerDto;
import ru.gb.spring.market.entities.Customer;
import ru.gb.spring.market.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id) {
        CustomerDto customer = userService.findById(id);

        return ResponseEntity.ok((customer));
    }
}
