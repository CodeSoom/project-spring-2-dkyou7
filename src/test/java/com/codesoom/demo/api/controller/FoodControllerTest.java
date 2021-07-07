package com.codesoom.demo.api.controller;

import com.codesoom.demo.domain.Food;
import com.codesoom.demo.service.FoodService;
import com.codesoom.demo.service.dto.FoodDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FoodController.class)
@DisplayName("FoodController TEST")
class FoodControllerTest {

    private MockMvc mvc;

    @MockBean
    private FoodService foodService;

    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new FoodController(foodService))
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .build();
    }

    @Test
    @DisplayName("음식 추가 테스트")
    void insertMemberTest() throws Exception {
        // given
        Food newFood = Food.builder()
                .name("chicken")
                .kcal(1004.64)
                .build();
        given(foodService.create(any()))
                .willReturn(FoodDto.Response.of(newFood));

        // when
        final ResultActions actions = mvc.perform(post("/foods")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(
                            "{" +
                                " \"foodName\" : \"chicken\", " +
                                " \"kcal\" : \"12043\" " +
                            "}")
                // Question : content 값이 완벽하지 않은데도 TEST 가 통과되었다.. 완벽하진 않은 것일까?
        );

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("data.food.kcal").value(1004.64))
                .andExpect(jsonPath("data.food.name").value("chicken"));
    }
}