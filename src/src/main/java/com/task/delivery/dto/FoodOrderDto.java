package com.task.delivery.dto;

import com.task.delivery.model.OrderMenu;

public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;

    public FoodOrderDto(OrderMenu orderMenu){
        this.name = orderMenu.getName();
        this.quantity= orderMenu.getQuantity();
        this.price= orderMenu.getPrice();
    }
}
