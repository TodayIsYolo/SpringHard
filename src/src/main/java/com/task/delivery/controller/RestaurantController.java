package com.task.delivery.controller;

import com.task.delivery.dto.RestaurantRequestDto;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.RestaurantRepository;
import com.task.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        // 로그인 되어 있는 ID의 username
        Restaurant restaurant = restaurantService.createRestaurant(requestDto);
        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }

}
