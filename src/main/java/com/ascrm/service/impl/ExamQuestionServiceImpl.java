package com.ascrm.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.Question;
import com.ascrm.handler.QuestionHandler;
import com.ascrm.handler.QuestionHandlerFactory;
import com.ascrm.mapper.QuestionMapper;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.entity.ExamQuestion;
import com.ascrm.mapper.ExamQuestionMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import java.util.List;

import static com.ascrm.entity.table.ExamPaperTableDef.EXAM_PAPER;
import static com.ascrm.entity.table.ExamQuestionTableDef.EXAM_QUESTION;
import static com.ascrm.entity.table.QuestionTableDef.QUESTION;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements ExamQuestionService {

    private final QuestionMapper questionMapper;

    private final QuestionHandlerFactory questionHandlerFactory;

    @Override
    public List<QuestionViewer> getQuestionViewerByExamPaperIdAndQuestionType(int examPaperId,int questionType) {
        //获取关联表信息
        List<ExamQuestion> examQuestionList = list(new QueryWrapper().select(EXAM_QUESTION.ALL_COLUMNS)
                .from(EXAM_QUESTION)
                .where(EXAM_QUESTION.EXAM_PAPER_ID.eq(examPaperId)
                .and(EXAM_QUESTION.IS_DELETE.eq(0))));
        if(CollectionUtils.isEmpty(examQuestionList)) return null;
        //从关联表中获取关联的题目ids
        List<Integer> questionIds = examQuestionList.stream().map(ExamQuestion::getQuestionId).toList();

        //获取指定类别的题目信息
        List<Question> questionList = questionMapper.selectListByQuery(new QueryWrapper()
                .where(QUESTION.IS_DELETED.eq(0))
                .and(QUESTION.ID.in(questionIds))
                .and(QUESTION.QUESTION_TYPE.eq(questionType)));
        if(CollectionUtils.isEmpty(questionList)) return null;
        List<Integer> ids = questionList.stream().map(Question::getId).toList();

        //在获取指定类别的题目的详细信息
        QuestionHandler handler = questionHandlerFactory.getHandler(questionType);
        List<QuestionViewer> questionViewerList = handler.getQuestionViewerByIds(ids);

        //转化
        return transform(questionViewerList, questionList);
    }

    public List<QuestionViewer> transform(List<QuestionViewer> questionViewerList,List<Question> questionList){
        for (QuestionViewer questionViewer : questionViewerList) {
            for (Question question : questionList) {
                if(questionViewer.getId().equals(question.getId()))
                    BeanUtil.copyProperties(question,questionViewer);
            }
        }
        return questionViewerList;
    }
}