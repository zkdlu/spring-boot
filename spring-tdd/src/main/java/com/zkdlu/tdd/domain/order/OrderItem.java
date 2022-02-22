package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDERITEMS")
public class OrderItem {
    @Id
    @Column(name = "ORDER_ITEM_ID")
    private String id;
    @Column(name = "ORDER_ITEM_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @Column(name = "COUNT")
    private int count;

    public OrderItem(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.menu = menu;
        this.count = 1;
    }

    public OrderItem(String id, String name, Menu menu, int count) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.count = count;
    }

    public void verify() {
        if (!name.equals(menu.getName())) {
            throw new IllegalStateException("기존 메뉴가 변경 됨");
        }
    }

    public int getMoney() {
        return menu.getPrice() * count;
    }
}
