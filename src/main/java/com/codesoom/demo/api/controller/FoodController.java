package com.codesoom.demo.api.controller;

import com.codesoom.demo.response.ApiResponseDto;
import com.codesoom.demo.service.FoodService;
import com.codesoom.demo.service.dto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ApiResponseDto<FoodDto.ResponseOne> create(@RequestBody @Valid FoodDto.Create create) {
        return ApiResponseDto.createOK(new FoodDto.ResponseOne(foodService.create(create)));
    }

    @PutMapping("{id}")
    public ApiResponseDto<FoodDto.ResponseOne> update(@PathVariable("id") Long id, @Valid @RequestBody FoodDto.Update update) {
        return ApiResponseDto.createOK(new FoodDto.ResponseOne(foodService.update(id, update)));
    }

    @DeleteMapping("{id}")
    public ApiResponseDto delete(@PathVariable("id") Long id) {
        foodService.delete(id);
        return ApiResponseDto.DEFAULT_OK;
    }

}
