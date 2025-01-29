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

    /**
     * 添加题目
     */
    void addQuestion(QuestionDTO questionDTO);

    /**
     * 删除题目
     */
    void deleteQuestion(Integer id);

    /**
     * 批量删除题目
     */
    void deleteQuestions(String ids);
}
