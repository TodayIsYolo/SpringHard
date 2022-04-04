package com.task.delivery.repository;

import com.task.delivery.model.Food;
import com.task.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findByRestaurantAndName(Restaurant restaurant, String name);

}
