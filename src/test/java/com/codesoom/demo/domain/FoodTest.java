package com.codesoom.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @ParameterizedTest
    @ValueSource(doubles = {1.1, 5.3, 124.3, 523.42})
    @DisplayName("칼로리는 양수여야만 합니다.")
    public void isPositive(double kcal){
        assertTrue(kcal > 0);
    }
}