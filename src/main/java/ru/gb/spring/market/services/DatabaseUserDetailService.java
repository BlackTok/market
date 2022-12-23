package ru.gb.spring.market.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.gb.spring.market.entities.Customer;
import ru.gb.spring.market.entities.Role;
import ru.gb.spring.market.repositories.RoleDao;
import ru.gb.spring.market.repositories.CustomerDao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DatabaseUserDetailService implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RoleDao roleDao;

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.findUserByUsername(username).get();
        Collection<Role> roles = customer.getRoles();
        //List<Role> roles = roleDao.getUserRolesByUserId(user.getId());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities(mapRolesToAuthorities(roles))
                .build();

        return userDetails;
    }

    public List<Customer> getAllUsers() {
        return customerDao.findAll();
    }

    public void addUser(Customer customer) {
        customerDao.save(customer);
    }

    public void deleteUser(Long id) {
        customerDao.deleteById(id);
    }

    public void changeRole(Customer customer, Long roleId, Boolean add) {
        Collection<Role> roles = customer.getRoles();
        if (add) {
            roles.add(roleDao.findById(roleId).get());
        } else {
            roles.removeIf(r -> r.getId().equals(roleId));
        }
        customer.setRoles(roles);

        customerDao.save(customer);
    }
}
