package ru.gb.MyMarket.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {
    /**
     Предлагаю сделать класс "Cart" (корзина), в корзине будет список "Item" (это таблица в базе) и конечная сумма заказа, в Item поля с названием продукта, его стоимостью за единицу, количеством продуктов и итоговая стоимость,
     возможно еще и Id продукта. Корзину сделать без таблицы, а использовать Redis. После того как клиент жмет кнопку "оформить заказ" мы этот заказ закидываем в базу, т.е. в базе будет таблица "Order",
     в которой будут поля (например, адрес доставки, телефон для связи и т.д., список "Item"  и общая стоимость, ну может еще поле для отображения что заказа сформирован->передан в доставку->доставлен) связь между Product и Item это ManyToMany
     между Item и Order тоже ManyToMany. В Order должно быть поле с User, т.к. мы формируем заказ для определенного User'a. Соответственно связь должна быть OneToMany, т.е. у одного User'a может быть много заказов, но у каждого заказа только один User.
     **/
    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }

}
