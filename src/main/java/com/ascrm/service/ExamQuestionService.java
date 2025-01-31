package com.ascrm.service;


import com.ascrm.entity.ExamQuestion;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 服务层。
 *
 * @author ascrm
 * @since 1.0
 */
public interface ExamQuestionService extends IService<ExamQuestion> {

    List<QuestionViewer> getQuestionViewerByExamPaperId(int examPaperId);
}