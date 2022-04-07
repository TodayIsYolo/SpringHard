package com.task.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    //음식에서 레스토랑 정보를 가져올 수 있음
    @ManyToOne
    @JoinColumn(name = "Restaurant_Id", nullable = false)
    private Restaurant restaurant;

    public Food(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant =restaurant;
    }
}
