package com.ascrm.enums;

import lombok.Getter;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Getter
public enum questionDifficutyEnum {
    ENTRY(1,"入门"),
    EASY(2,"简单"),
    NORMAL(3,"普通"),
    MEDIUM(4,"中等"),
    HARD(5,"困难");

    private final Integer value;
    private final String label;

    questionDifficutyEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static questionDifficutyEnum getByCode(Integer val) {
        for (questionDifficutyEnum item : values()) {
            if (item.value.equals(val)) {
                return item;
            }
        }
        return null;
    }
}
