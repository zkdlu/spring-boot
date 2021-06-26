package com.zkdlu.tdd.domain.shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "SHOPS")
public class Shop {
    @Id
    @Column(name = "SHOP_ID")
    private String id;
    @Column
    private String name;
    @Column
    private boolean open;
    @Column
    private int minPrice;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHOP_ID")
    private List<Menu> menus = new ArrayList<>();

    public Shop(String id, String name, int minPrice) {
        this.id = id;
        this.name = name;
        this.minPrice = minPrice;
    }

    public void addMenu(Menu menu) {
        this.menus.add(menu);
    }

    public boolean verifyOrderMoney(int orderMoney) {
        return orderMoney >= minPrice;
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }
}
