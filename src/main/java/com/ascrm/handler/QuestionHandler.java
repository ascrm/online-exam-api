package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.viewer.QuestionViewer;

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
     * 修改题目信息
     */
    void updateQuestion(QuestionDTO questionDTO);

    /**
     * 删除题目
     */
    void deleteQuestion(Integer id);

    /**
     * 批量删除题目
     */
    void deleteQuestions(String ids);

    /**
     * 根据id查询题目详细信息
     */
    QuestionViewer getQuestionViewerById(QuestionViewer questionViewer);
}
