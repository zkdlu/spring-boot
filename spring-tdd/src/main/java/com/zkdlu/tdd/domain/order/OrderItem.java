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
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;
    @Column(name = "COUNT")
    private int count;
}
