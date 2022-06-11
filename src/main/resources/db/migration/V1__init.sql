create table products
    (
    id bigserial primary key,
    title varchar (255),
    price int
    );
insert into products(title, price)
values ('Milk', 1),
('Bread', 2),
('Cucumber', 4),
('Tomato', 3),
('Cheese', 6)