package com.ascrm.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.Question;
import com.ascrm.enums.QuestionTypeEnum;
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

import java.util.ArrayList;
import java.util.List;

import static com.ascrm.entity.table.ExamQuestionTableDef.EXAM_QUESTION;

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
    public List<QuestionViewer> getQuestionViewerByExamPaperId(int examPaperId) {
        List<ExamQuestion> examQuestionList = list(new QueryWrapper().select(EXAM_QUESTION.ALL_COLUMNS)
                .from(EXAM_QUESTION)
                .where(EXAM_QUESTION.EXAM_PAPER_ID.eq(examPaperId)));
        List<Integer> questionIds = examQuestionList.stream().map(ExamQuestion::getQuestionId).toList();
        List<Question> questions = questionMapper.selectListByIds(questionIds);

        List<QuestionViewer> list=new ArrayList<>();
        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            List<Question> typeQuestionList = questions.stream().filter(question ->
                    questionTypeEnum.getValue().equals(question.getQuestionType())
            ).toList();

            List<Integer> ids = typeQuestionList.stream().map(Question::getId).toList();

            QuestionHandler handler = questionHandlerFactory.getHandler(questionTypeEnum.getValue());
            List<QuestionViewer> typeQuestionViewerList = handler.getQuestionViewerByIds(ids);
            List<QuestionViewer> transformList = transform(typeQuestionViewerList, typeQuestionList);
            CollectionUtils.addAll(list,transformList);
        }
        return list;
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