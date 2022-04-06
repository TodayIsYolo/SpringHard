package com.task.delivery.controller;


import com.task.delivery.dto.FoodRequestDto;
import com.task.delivery.dto.OrderDto;
import com.task.delivery.dto.OrderRequestDto;
import com.task.delivery.model.Orders;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.OrdersRepository;
import com.task.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    //주문 들어옴
    private final OrderService orderService;
    private final OrdersRepository ordersRepository;
    @PostMapping("/order/request")
    public Orders takeOrder(@RequestBody OrderRequestDto requestDtoList) {
        return orderService.order(requestDtoList);
    }

    @GetMapping("/orders")
    public List<Orders> getRestaurant() {
        return ordersRepository.findAll();
    }




}


