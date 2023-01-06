package ru.gb.spring.market.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gb.spring.market.dto.CustomerDto;
import ru.gb.spring.market.entities.Customer;

public interface IUserServiceDetail {
    UserDetails loadUserByUserUsername(String username) throws UsernameNotFoundException;

    CustomerDto findById(Long id);
}
