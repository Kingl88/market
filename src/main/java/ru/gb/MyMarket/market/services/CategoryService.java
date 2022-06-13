package ru.gb.MyMarket.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.MyMarket.market.models.Category;
import ru.gb.MyMarket.market.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
       return categoryRepository.findByTitle(title);
    }
}
