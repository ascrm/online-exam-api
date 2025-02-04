package com.ascrm.service;


import com.ascrm.entity.HistoryExam;
import com.ascrm.viewer.HistoryExamViewer;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * 服务层。
 *
 * @author ascrm
 * @since 1.0
 */
public interface HistoryExamService extends IService<HistoryExam> {

    List<HistoryExamViewer> getHistoryExamQuestionList(Integer examPaperId);
}