package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.MyMarket.market.dto.ProductDto;
import ru.gb.MyMarket.market.models.Product;
import ru.gb.MyMarket.market.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


        @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam (name = "page", defaultValue = "1") int pageIndex) {
            if(pageIndex < 1){
                pageIndex = 1;
            }
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
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
    public void deleteById(@PathVariable Long id, @RequestParam (name = "page", defaultValue = "1") int pageIndex) {
        productService.deleteById(id);
    }
}
