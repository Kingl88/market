package ru.gb.MyMarket.market.mapper.product;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.MyMarket.market.dto.ProductDto;
import ru.gb.MyMarket.market.models.Product;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mapping(target = "categoryTitle", source = "category.title")
    ProductDto mapToDto(Product product);
}
