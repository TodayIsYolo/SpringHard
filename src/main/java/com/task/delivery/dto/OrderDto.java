package com.task.delivery.dto;

import com.task.delivery.model.Orders;

import java.util.List;

public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto (String restaurantName, List<FoodOrderDto> foods, int deliveryFee, int totalPrice){
        this.restaurantName = restaurantName;
        this.foods=foods;
        this.deliveryFee=deliveryFee;
        this.totalPrice=totalPrice;
    }

}
