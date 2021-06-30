package com.codesoom.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 음식 정보.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    private String name;

    private Long kcal;

    private LocalDateTime eatTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Food(Long id, String name, Long kcal, LocalDateTime eatTime, Member member) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.eatTime = eatTime;
        this.member = member;
    }

    public Food update(String foodName, Long kcal, Member member) {
        this.name = foodName;
        this.kcal = kcal;
        this.member = member;
        return this;
    }
}
