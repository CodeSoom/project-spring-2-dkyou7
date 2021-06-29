package com.codesoom.demo.service.dto;

import com.codesoom.demo.domain.Food;
import com.codesoom.demo.domain.Member;
import com.codesoom.demo.domain.MemberRepository;
import com.codesoom.demo.exception.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class FoodDto {

    /**
     * 음식 리스트를 반환하는 DTO 클래스
     */
    @Getter
    public static class ResponseList {

        private List<Response> foods;

        public ResponseList(List<Response> sources){
            this.foods = sources;
        }

    }

    /**
     * 음식 하나를 반환하는 DTO 클래스
     */
    @Getter
    public static class ResponseOne {

        private Response food;

        public ResponseOne(Response source) {
            this.food = source;
        }
    }

    @Getter
    public static class Response {

        private Long id;
        private String name;
        private Long kcal;
        private LocalDateTime eatTime;
        private Member member;

        @Builder
        public Response(Long id, String name, Long kcal, LocalDateTime eatTime, Member member) {
            this.id = id;
            this.name = name;
            this.kcal = kcal;
            this.eatTime = eatTime;
            this.member = member;
        }

        public static Response of(Food source) {
            return Response.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .kcal(source.getKcal())
                    .eatTime(source.getEatTime())
                    .member(source.getMember())
                    .build();
        }
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class Create {

        @NotBlank
        private String foodName;
        @NotBlank
        private Long kcal;
        @NotBlank
        private LocalDateTime eatTime;

        private Member member;

        private final MemberRepository memberRepository;

        public Food toEntity() {
            Member findMember = memberRepository
                    .findById(this.member.getId())
                    .orElseThrow(NotFoundException::new);

            return Food.builder()
                    .name(foodName)
                    .kcal(kcal)
                    .eatTime(eatTime)
                    .member(findMember)
                    .build();
        }
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public class Update {
        @NotEmpty
        private String foodName;
        @NotEmpty
        private Long kcal;
        @NotBlank
        private LocalDateTime eatTime;

        private Member member;

        private final MemberRepository memberRepository;

        public Food apply(Food food) {
            Member findMember = memberRepository
                    .findById(this.member.getId())
                    .orElseThrow(NotFoundException::new);

            return food.update(foodName,kcal,eatTime,findMember);
        }
    }
}
