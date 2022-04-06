package com.task.delivery.repository;

import com.task.delivery.model.Food;
import com.task.delivery.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {


}