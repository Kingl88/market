package ru.gb.MyMarket.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.MyMarket.market.models.Role;
import ru.gb.MyMarket.market.models.User;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
