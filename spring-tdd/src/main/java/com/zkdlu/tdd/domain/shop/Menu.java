package com.zkdlu.tdd.domain.shop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "MENUS")
public class Menu {
    @Id
    @Column(name = "MENU_ID")
    private String id;
    @Column
    private String name;
    @Column
    private int price;
    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Shop shop;

    public Menu(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        
        verifyMenu();
    }

    private void verifyMenu() {
        if (id == null || id.isBlank()) {
            throw new IllegalStateException("메뉴 id가 없습니다.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalStateException("메뉴 이름이 없습니다.");
        }

        if (price < 0) {
            throw new IllegalStateException("가격은 음수일 수 없습니다.");
        }
    }
}
