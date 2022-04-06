package com.task.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderMenu")
    private List<OrderMenu> foods;

    public Orders(String restaurantName, int totalPrice, List<OrderMenu> foods, int deliveryFee) {
        this.restaurantName =restaurantName;
        this.totalPrice = totalPrice;
        this.foods =foods;
        this.deliveryFee = deliveryFee;
    }
}
