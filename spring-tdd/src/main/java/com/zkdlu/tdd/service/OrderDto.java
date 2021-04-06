package com.zkdlu.tdd.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private String shopId;
    private List<String> menuIds = new ArrayList<>();

}
