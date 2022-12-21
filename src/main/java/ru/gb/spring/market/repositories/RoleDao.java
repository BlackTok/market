package ru.gb.spring.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.market.entities.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
}
