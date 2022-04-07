package com.task.delivery.repository;

import com.task.delivery.model.OrderMenu;
import com.task.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
