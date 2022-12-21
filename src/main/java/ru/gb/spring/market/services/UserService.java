package ru.gb.spring.market.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.spring.market.converters.CustomerConverter;
import ru.gb.spring.market.dto.CustomerDto;
import ru.gb.spring.market.entities.Customer;
import ru.gb.spring.market.entities.Role;
import ru.gb.spring.market.repositories.CustomerDao;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserServiceDetail {
    private final CustomerDao customerDao;
    private final CustomerConverter customerConverter;

    public Customer findByUserUsername(String username) {
        return customerDao.findUserByUsername(username).get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUserUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUserUsername(username);
        return new org.springframework.security.core.userdetails.User(
                customer.getUsername(), customer.getPassword(), mapRolesToAuthorities(customer.getRoles())
        );
    }

    @Override
    @Transactional
    public CustomerDto findById(Long id) {
        return customerConverter.EntityToDto(customerDao.findById(id).get());
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
