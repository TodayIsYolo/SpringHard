package com.task.delivery.service;


import com.task.delivery.dto.*;
import com.task.delivery.model.Food;
import com.task.delivery.model.OrderMenu;
import com.task.delivery.model.Orders;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.FoodRepository;
import com.task.delivery.repository.OrderMenuRepository;
import com.task.delivery.repository.OrdersRepository;
import com.task.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    //int totalPrice = 0;
    @Transactional
    public Orders order(OrderRequestDto orderRequestDto){
        Long restaurnatId = orderRequestDto.getRestaurantId();
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurnatId);
        Restaurant restaurant = foundRestaurant.get();
        String restaurantName = restaurant.getName();


        List<FoodOrderRequestDto> infoRequestDtosList = orderRequestDto.getFoods();
        List<FoodOrderDto> fakeFoods = new ArrayList<>();
        List<OrderMenu> foods = new ArrayList<>();

        int totalPrice = 0;

        for(FoodOrderRequestDto info:infoRequestDtosList){
            Long foodId = info.getId();
            int quantity = info.getQuantity();


            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("수량을 확인해 주세요");
            }

            Food food = foodRepository.findById(foodId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않습니다.")
            );
            String name = food.getName();
            int price = food.getPrice();
            price *= quantity;

            totalPrice+=price;

            OrderMenu orderMenu = new OrderMenu(name,price,quantity);
            FoodOrderDto foodOrderDto = new FoodOrderDto(orderMenu);
            System.out.println(foodOrderDto);
            fakeFoods.add(foodOrderDto);
            foods.add(orderMenu);
            orderMenuRepository.save(orderMenu);
            //총가격
        }
//문제푸드가 저장이 안됨
        if(totalPrice<restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("주문 최소금액을 맞춰주세요");
        }
        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        Orders orders =new Orders(restaurantName,totalPrice,foods,deliveryFee);
        System.out.println(orders.getFoods());
        //OrderDto orderDto = new OrderDto(restaurantName,fakeFoods,deliveryFee,totalPrice);
        ordersRepository.save(orders);

        return orders;
        //배달비+총가격
        //오더디티오,오더스에 저장
        //return 값쓰기
        //가격 계산하기
    }



}
