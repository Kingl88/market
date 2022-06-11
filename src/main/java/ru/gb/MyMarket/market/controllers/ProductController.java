package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.MyMarket.market.models.Product;
import ru.gb.MyMarket.market.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @GetMapping("/products/price")
    public List<Product> findByPrice(@RequestParam(name = "min", required = false) Integer minPrice, @RequestParam(name = "max", required = false) Integer maxPrice) {
        if (minPrice == null) {
            return productService.findByPriceBefore(maxPrice);
        }
        if (maxPrice == null) {
            return productService.findByPriceAfter(minPrice);
        } else {
            return productService.findByPriceBetween(minPrice, maxPrice);
        }
    }

    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/products/delete/{id}")
    public List<Product> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return productService.findAll();
    }
}
