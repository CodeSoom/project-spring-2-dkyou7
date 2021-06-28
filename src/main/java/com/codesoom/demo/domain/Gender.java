package com.codesoom.demo.domain;

import com.codesoom.demo.utils.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender implements EnumType {
    MALE("남자"),FEMALE("여자");

    private final String text;

    @Override
    public String getId() {
        return this.name();
    }
}
