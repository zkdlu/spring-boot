package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public void verify() {
        if (!name.equals(menu.getName())) {
            throw new IllegalStateException("기존 메뉴가 변경 됨");
        }
    }

    public int getMoney() {
        return menu.getPrice() * count;
    }
}
