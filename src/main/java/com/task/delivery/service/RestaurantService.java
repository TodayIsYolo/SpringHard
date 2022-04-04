package com.task.delivery.service;

import com.task.delivery.dto.RestaurantRequestDto;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository RestaurantRepository;

    @Transactional
    public Restaurant createRestaurant(RestaurantRequestDto restaurantRequestDto){
        String name = restaurantRequestDto.getName();
        int minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        int deliveryFee = restaurantRequestDto.getDeliveryFee();

        //유효성 검사 만들기
        minOrderPriceCheck(minOrderPrice);

        deliveryFeeCheck(deliveryFee);


        Restaurant restaurant = new Restaurant(name, minOrderPrice, deliveryFee);
        RestaurantRepository.save(restaurant);
        return restaurant;
    }

    //유효성
    //최소가격
    public void minOrderPriceCheck (int minOrderPrice){
        if(1000 > minOrderPrice || minOrderPrice > 100000) {
            throw new IllegalArgumentException("최소주문가격을 1000~100,000원으로 설정해 주세요");
        }

        if(minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로만 입력 가능합니다.");
        }
    }
    //배달비
    public void deliveryFeeCheck (int deliveryFee){
        if(0 > deliveryFee || deliveryFee > 10000) {
            throw new IllegalArgumentException("최소주문가격을 1000~100,000원으로 설정해 주세요");
        }

        if(deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("100원 단위로만 입력 가능합니다.");
        }
    }


}
