package com.codesoom.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 회원 정보.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private Long height;    // 키

    private Long weight;    // 몸무게

    private Gender gender;

    private LocalDate birthDate;

    private String bmr;     // 기초대사량.

    @Builder
    private Member(String username, String password, String nickname, Long height, Long weight, Gender gender, LocalDate birthDate, String bmr) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bmr = bmr;
    }
}
