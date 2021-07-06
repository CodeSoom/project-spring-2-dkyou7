package com.codesoom.demo.service.dto;

import com.codesoom.demo.domain.Food;
import com.codesoom.demo.domain.Member;
import lombok.Builder;
import lombok.Getter;
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
        private Double kcal;
        private LocalDateTime eatTime;
        private Member member;

        @Builder
        public Response(Long id, String name, Double kcal, LocalDateTime eatTime, Member member) {
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
    public static class Create {

        @NotBlank
        private String foodName;
        @NotBlank
        private String kcal;

        private Member member;

        public Food toEntity() {
            return Food.builder()
                    .name(foodName)
                    .kcal(Double.parseDouble(kcal))
                    .eatTime(LocalDateTime.now())
                    .member(member)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class Update {
        @NotEmpty
        private String foodName;

        private String kcal;

        private Member member;

        public Food apply(Food food) {
            return food.update(foodName,Double.parseDouble(kcal),member);
        }
    }
}
