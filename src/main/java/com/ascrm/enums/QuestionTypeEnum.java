package com.ascrm.enums;

import lombok.Getter;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Getter
public enum QuestionTypeEnum {
    SINGLE_CHOICE(1,"单选题"),
    MULTIPLE_CHOICE(2,"多选题"),
    JUDGE(3,"判断题");

    private final Integer value;
    private final String label;

    QuestionTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static QuestionTypeEnum getByCode(Integer val) {
        for (QuestionTypeEnum item : values()) {
            if (item.getValue().equals(val)) {
                return item;
            }
        }
        return null;
    }
}
