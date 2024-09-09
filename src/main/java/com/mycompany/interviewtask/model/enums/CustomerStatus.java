package com.mycompany.interviewtask.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerStatus {
    GOLD(100),
    PLATINUM(1000),
    SILVER(10);
    private final int bonus;

    @JsonValue
    public String toJson() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static CustomerStatus fromJson(String value) {
        return CustomerStatus.valueOf(value.toUpperCase());
    }
}
