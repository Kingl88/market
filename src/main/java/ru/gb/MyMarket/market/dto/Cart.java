package ru.gb.MyMarket.market.dto;

import lombok.Getter;
import lombok.Setter;
import ru.gb.MyMarket.market.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Setter
@Getter
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product) {
        if (increment(product.getId())) {
            return;
        }
        items.add(new CartItem(product));
        recalculate();
    }

    private boolean increment(Long productId) {//если продукт с productId есть в списке, то увеличиваем его число на 1.
       return items.stream().filter(item->item.getProductId().equals(productId)).peek(item-> {
            item.changeCount(1);
            recalculate();
        }).anyMatch(item->item.getProductId().equals(productId));
//        for (CartItem item : items) {
//            if (item.getProductId().equals(productId)) {
//                item.changeCount(1);
//                recalculate();
//                return true;
//            }
//        }
//        return false;
    }

    public void decrement(Long productId) {//метод для уменьшения количества продукта в корзине, при условии если колчество продукта становится <=0 то удаляем продукт из списка.
        items.stream().filter(c -> c.getProductId().equals(productId)).peek(c -> c.changeCount(-1)).collect(Collectors.toList());
        items.removeIf(c -> c.getCount() <= 0);
        recalculate();
//        Iterator<CartItem> iterator = items.iterator();
//        while (iterator.hasNext()){
//            CartItem item = iterator.next();
//            if(item.getProductId().equals(productId)){
//                item.changeCount(-1);
//                if(item.getCount() <= 0){
//                    iterator.remove();
//                }
//                recalculate();
//                return;
//            }
        //       }
    }

    public void remove(Long productId) {//удаление продукта из корзины
        items.removeIf(item -> item.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {//очистка корзины
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() { //метод для пересчета общей суммы продуктов в корзине
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void merge(Cart another) {//метод для слияния корзин
        for (CartItem anotherItem : another.items) {//итерируемся по корзине которую надо "влить" в существующую
            boolean merged = false;//флаг для определения произошло слияние продуктов или нет
            for (CartItem myItem : items) {//итерируемся по корзине основной
                if (myItem.getProductId().equals(anotherItem.getProductId())) {//если Id продукта в текущей корзине совпадаетс Id продукта в корзине для слияния
                    myItem.changeCount(anotherItem.getCount());//пересчитываем количество продукта в текущей корзине
                    merged = true;//переводим флаг в состояние true, что означает слияние продукта произошло
                    break;//выходим из цикла, так как дальше нет смысла проверять
                }
            }
            if (!merged) {//если после того как мы сравнили продукт из корзины для слияния, со всеми продуктами в текущей корзине и не нашли совпадения,
                items.add(anotherItem);//добавляем этот продукт в текущую корзину
            }
        }
        recalculate();//пересчет стоимости корзины
        another.clear();//очищаем корзину, которую вливали в текущую корзину.
    }
}
