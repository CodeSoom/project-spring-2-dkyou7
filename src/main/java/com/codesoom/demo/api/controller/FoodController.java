package com.codesoom.demo.api.controller;

import com.codesoom.demo.response.ApiResponseDto;
import com.codesoom.demo.service.FoodService;
import com.codesoom.demo.service.dto.FoodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 음식에 대한 HTTP 요청 처리를 담당합니다.
 */
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

    /**
     * 전달된 음식 정보로 음식을 생성한 후, 생성된 음식정보를 반환합니다.
     *
     * @param create 음식 정보.
     * @return 생성된 음식.
     */
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<FoodDto.ResponseOne> create(@RequestBody @Valid FoodDto.Create create) {
        return ApiResponseDto.createOK(new FoodDto.ResponseOne(foodService.create(create)));
    }

    /**
     * 전달된 식별자에 해당하는 음식을 찾고, 주어진 음식 정보로 수정한 후 수정된 정보를 반환합니다.
     *
     * @param id      음식 식별자.
     * @param update  수정할 음식 정보.
     * @return 수정된 음식.
     */
    @PutMapping("{id}")
    public ApiResponseDto<FoodDto.ResponseOne> update(@PathVariable("id") Long id, @Valid @RequestBody FoodDto.Update update) {
        return ApiResponseDto.createOK(new FoodDto.ResponseOne(foodService.update(id, update)));
    }

    /**
     * 전달된 식별자에 해당하는 음식을 삭제합니다.
     *
     * @param id 음식 식별자
     */
    @DeleteMapping("{id}")
    public ApiResponseDto<String> delete(@PathVariable("id") Long id) {
        foodService.delete(id);
        return ApiResponseDto.DEFAULT_OK;
    }

}
