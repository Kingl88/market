package ru.gb.MyMarket.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.MyMarket.market.models.Category;
import ru.gb.MyMarket.market.models.Product;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<Product> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = getProducts();
    }
}
