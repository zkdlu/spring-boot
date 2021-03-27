package com.zkdlu.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import com.zkdlu.hexagonal.orderapp.domain.order.Receipt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Entity
@Table(name = "orderRecord")
public class OrderRecord {
    @Id
    @GeneratedValue
    private long orderId;
    private int money;

    static OrderRecord from(Receipt receipt) {
        return new OrderRecord(receipt.getOrderId(), receipt.getMoney());
    }
}
