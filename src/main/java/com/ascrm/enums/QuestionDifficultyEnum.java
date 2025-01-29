package com.ascrm.enums;

import lombok.Getter;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Getter
public enum QuestionDifficultyEnum {
    ENTRY(1,"入门"),
    EASY(2,"简单"),
    NORMAL(3,"普通"),
    MEDIUM(4,"中等"),
    HARD(5,"困难");

    private final Integer value;
    private final String label;

    QuestionDifficultyEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static QuestionDifficultyEnum getByCode(Integer val) {
        for (QuestionDifficultyEnum item : values()) {
            if (item.value.equals(val)) {
                return item;
            }
        }
        return null;
    }
}
