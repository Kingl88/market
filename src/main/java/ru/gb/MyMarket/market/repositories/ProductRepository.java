package ru.gb.MyMarket.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.MyMarket.market.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(int minPrice, int maxPrice);

    List<Product> findByPriceAfter(int maxPrice);

    List<Product> findByPriceBefore(int minPrice);

    void deleteById(Long id);
}
