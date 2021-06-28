package com.codesoom.demo.service;

import com.codesoom.demo.domain.FoodRepository;
import com.codesoom.demo.exception.NotFoundException;
import com.codesoom.demo.service.dto.FoodDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 음식정보를 처리하는 비즈니스 로직.
 */
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * 음식 리스트를 반환합니다.
     *
     * @return foodList 음식 리스트.
     */
    public List<FoodDto.Response> findAll() {
        return foodRepository.findAll()
                .stream()
                .map(FoodDto.Response::of)
                .collect(Collectors.toList());
    }

    /**
     * 식별자에 대한 음식을 반환합니다.
     *
     * @param id 음식 식별자.
     * @return food 음식.
     * @throws NotFoundException 해당 식별자에 대한 정보를 찾을 수 없습니다.
     */
    public FoodDto.Response findById(Long id) {
        return foodRepository.findById(id)
                .map(FoodDto.Response::of)
                .orElseThrow(NotFoundException::new);
    }
}
