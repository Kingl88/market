package ru.gb.MyMarket.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.MyMarket.market.models.Role;
import ru.gb.MyMarket.market.models.User;
import ru.gb.MyMarket.market.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
