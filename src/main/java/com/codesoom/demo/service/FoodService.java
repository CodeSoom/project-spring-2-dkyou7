package com.codesoom.demo.service;

import com.codesoom.demo.domain.Food;
import com.codesoom.demo.domain.FoodRepository;
import com.codesoom.demo.exception.NotFoundException;
import com.codesoom.demo.service.dto.FoodDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 음식정보를 처리하는 비즈니스 로직을 담당합니다.
 */
@Service
@Transactional
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
     * 전달된 식별자에 대한 음식을 반환합니다.
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

    /**
     * 음식을 생성합니다.
     *
     * @param create 생성하고자하는 정보.
     * @return FoodDto.Response.of(savedFood) 생성된 정보를 DTO에 담아 반환.
     */
    public FoodDto.Response create(FoodDto.Create create) {
        Food food = create.toEntity();
        Food savedFood = foodRepository.save(food);
        return FoodDto.Response.of(savedFood);
    }

    /**
     * 식별자를 매개변수로 음식을 찾고, 변경된 정보를 반환합니다.
     *
     * @param id 변경하고자하는 식별자.
     * @param update 변경하고자하는 정보.
     * @return FoodDto.Response.of(updatedFood) 변경된 정보를 DTO에 담아 반환.
     */
    public FoodDto.Response update(Long id, FoodDto.Update update) {
        Food updatedFood = foodRepository.findById(id)
                .map(update::apply)
                .orElseThrow(NotFoundException::new);

        return FoodDto.Response.of(updatedFood);
    }

    /**
     * 식별자를 매개변수로 음식을 찾고, 제거합니다.
     *
     * @param id 삭제하고자하는 식별자.
     */
    public void delete(Long id) {
        Food deletedFood = foodRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        foodRepository.delete(deletedFood);
    }
}
