package com.task.delivery.service;

import com.task.delivery.dto.FoodRequestDto;
import com.task.delivery.model.Food;
import com.task.delivery.model.Restaurant;
import com.task.delivery.repository.FoodRepository;
import com.task.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FoodService {
    private  final FoodRepository foodRepository;
    private final RestaurantRepository RestaurantRepository;

    //음식등록
   @Transactional
    public void createFood(Long restaurantId, List<FoodRequestDto> requestDtoList) {
//        Optional<Restaurant> foundRestaurant = RestaurantRepository.findById(restaurantId);
//        //optional 값을 받는 방법
//        Restaurant restaurant = foundRestaurant.get();

       Restaurant restaurant = RestaurantRepository.findById(restaurantId).orElseThrow(
               () -> new IllegalArgumentException("존재하지 않습니다.")
       );
        for(FoodRequestDto requestDto:requestDtoList){
            String name =requestDto.getName();
            int price = requestDto.getPrice();

            // 유효성 검사 필요
            //입력할 때 같은 음식 입력
//            if(requestDtoList.contains(requestDto))
//                throw new IllegalArgumentException("이미 입력한 메뉴입니다.");
            //같은 음식점에 같은 음식X
            sameNameCheck(restaurant,name);
            //가격 100-1,000,000
            priceCheck(price);
            //100단위
            Food food = new Food(name, price, restaurant);
            System.out.println(food);
            foodRepository.save(food);
        }
    }


    public  void sameNameCheck(Restaurant restaurant, String name){
        Optional<Food> food = foodRepository.findByRestaurantAndName(restaurant, name);
        if (food.isPresent())
            throw new IllegalArgumentException("동일한 메뉴가 존재합니다.");

    }

    public  void priceCheck(int price){
        if(100 > price || price > 1000000) {
            throw new IllegalArgumentException("최소주문가격을 1000~100,000원으로 설정해 주세요");
        }

        if(price % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로만 입력 가능합니다.");
        }
    }

}
