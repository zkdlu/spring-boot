drop table if exists menus CASCADE;
drop table if exists orderitems CASCADE;
drop table if exists orders CASCADE;
drop table if exists shops CASCADE;

create table menus (
    menu_id varchar(255) not null,
    name varchar(255),
    price integer,
    shop_id varchar(255),
    primary key (menu_id)
);

create table shops (
    shop_id varchar(255) not null,
    min_price integer,
    name varchar(255),
    open boolean,
    primary key (shop_id)
);

create table orders (
    order_id varchar(255) not null,
    order_state varchar(255),
    shop_id varchar(255),
    primary key (order_id)
);

create table orderitems (
    order_item_id varchar(255) not null,
    count integer,
    order_item_name varchar(255),
    menu_id varchar(255),
    order_id varchar(255),
    primary key (order_item_id)
);

alter table menus add constraint FK_menus_shop_id foreign key (shop_id) references shops;
alter table orderitems add constraint FK_orderitems_menu_id foreign key (menu_id) references menus;
alter table orders add constraint FK_orders_shop_id foreign key (shop_id) references shops;