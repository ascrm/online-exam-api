package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.Question;
import com.ascrm.enums.QuestionTypeEnum;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
public interface QuestionHandler {

    QuestionTypeEnum getQuestionTypeEnum();

    void addQuestion(QuestionDTO questionDTO);
}
