package com.mycompany.interviewtask.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerStatus {
    GOLD(100),
    PLATINUM(1000),
    SILVER(10);
    private final int bonus;
}
