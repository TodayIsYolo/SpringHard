package com.task.delivery.controller;



import com.task.delivery.dto.FoodRequestDto;
import com.task.delivery.dto.RestaurantRequestDto;
import com.task.delivery.model.Food;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.FoodRepository;
import com.task.delivery.repository.RestaurantRepository;
import com.task.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;
    private final RestaurantRepository restaurantRepository;

    //음식점 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createRestaurant(@PathVariable Long restaurantId,@RequestBody List<FoodRequestDto> requestDtoList) {
        // 로그인 되어 있는 ID의 username
        foodService.createFood(restaurantId, requestDtoList);
    }

    //음식조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId){
        Optional<Restaurant> foundrestaurant = restaurantRepository.findById(restaurantId);
        //Restaurant restaurant = restaurantRepository.findById(restaurantId)
        //                .orElseThrow(
        //                        () -> new NullPointerException("음식점이 돈재하지 않습니다."));
        //null 안됨
        Restaurant restaurant = foundrestaurant.get();

        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}
