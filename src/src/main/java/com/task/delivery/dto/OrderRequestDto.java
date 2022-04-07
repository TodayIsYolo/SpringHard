package com.task.delivery.dto;

import com.task.delivery.model.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    //private List<OrderMenu> foods;
    private List<FoodOrderRequestDto> foods;
}
