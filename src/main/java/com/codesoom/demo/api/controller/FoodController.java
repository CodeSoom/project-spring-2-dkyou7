package com.codesoom.demo.api.controller;

import com.codesoom.demo.response.ApiResponseDto;
import com.codesoom.demo.service.FoodService;
import com.codesoom.demo.service.dto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ApiResponseDto<FoodDto.ResponseList> findAll(){
        return ApiResponseDto.createOK(new FoodDto.ResponseList(foodService.findAll()));
    }

    @GetMapping("{id}")
    public ApiResponseDto<FoodDto.ResponseOne> findById(@PathVariable("id") Long id){
        return ApiResponseDto.createOK(new FoodDto.ResponseOne(foodService.findById(id)));
    }
}
