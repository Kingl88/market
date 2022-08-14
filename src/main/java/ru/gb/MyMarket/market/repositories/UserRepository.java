package ru.gb.MyMarket.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.MyMarket.market.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
