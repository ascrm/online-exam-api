package com.ascrm.service;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.Question;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.service.IService;

/**
 * 服务层。
 *
 * @author ascrm
 * @since 1.0
 */
public interface QuestionService extends IService<Question> {

    void addQuestion(QuestionDTO questionDTO);

    void updateQuestion(QuestionDTO questionDTO);

    void deleteQuestion(int id);

    void deleteQuestions(String ids);

    QuestionViewer getQuestionViewerById(int id);
}