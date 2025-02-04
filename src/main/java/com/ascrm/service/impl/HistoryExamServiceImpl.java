package com.ascrm.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.HistoryExamQuestion;
import com.ascrm.entity.Question;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.handler.QuestionHandler;
import com.ascrm.handler.QuestionHandlerFactory;
import com.ascrm.mapper.HistoryExamQuestionMapper;
import com.ascrm.mapper.QuestionMapper;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.HistoryExamViewer;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.ascrm.service.HistoryExamService;
import com.ascrm.entity.HistoryExam;
import com.ascrm.mapper.HistoryExamMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static com.ascrm.entity.table.HistoryExamQuestionTableDef.HISTORY_EXAM_QUESTION;
import static com.ascrm.entity.table.HistoryExamTableDef.HISTORY_EXAM;
import static com.ascrm.entity.table.QuestionTableDef.QUESTION;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class HistoryExamServiceImpl extends ServiceImpl<HistoryExamMapper, HistoryExam> implements HistoryExamService {

    private final HistoryExamQuestionMapper historyExamQuestionMapper;

    private final QuestionMapper questionMapper;

    private final QuestionHandlerFactory questionHandlerFactory;

    @Override
    public List<HistoryExamViewer> getHistoryExamQuestionList(Integer examPaperId) {
        HistoryExam historyExam = getOne(new QueryWrapper().where(HISTORY_EXAM.IS_DELETE.eq(0))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.eq(examPaperId))
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername())));
        //查询tb_history_exam_question表
        List<HistoryExamQuestion> historyExamQuestionList = historyExamQuestionMapper.selectListByQuery(new QueryWrapper().where(HISTORY_EXAM_QUESTION.IS_DELETE.eq(0))
                .and(HISTORY_EXAM_QUESTION.HISTORY_EXAM_ID.eq(historyExam.getId())));
        List<Integer> questionIds = historyExamQuestionList.stream().map(HistoryExamQuestion::getQuestionId).toList();

        //查询tb_question表
        List<Question> questions = questionMapper.selectListByQuery(new QueryWrapper().where(QUESTION.IS_DELETED.eq(0))
                .and(QUESTION.ID.in(questionIds)));

        List<HistoryExamViewer> list = new ArrayList<>();
        for (QuestionTypeEnum questionTypeEnum : QuestionTypeEnum.values()) {
            List<Integer> ids = questions.stream().filter(question ->
                    questionTypeEnum.getValue().equals(question.getQuestionType())
            ).map(Question::getId).toList();
            QuestionHandler handler = questionHandlerFactory.getHandler(questionTypeEnum.getValue());
            List<QuestionViewer> questionViewerList = handler.getQuestionViewerByIds(ids);
            List<QuestionViewer> xxxxxxxx = transform1(questionViewerList, questions);
            List<HistoryExamViewer> historyExamViewers = transform2(xxxxxxxx, historyExamQuestionList);
            CollectionUtils.addAll(list,historyExamViewers);
        }
        return list;
    }

    public List<QuestionViewer> transform1(List<QuestionViewer> questionViewerList, List<Question> questionList){
        for (QuestionViewer questionViewer : questionViewerList) {
            for (Question question : questionList) {
                if(questionViewer.getId().equals(question.getId())){
                    questionViewer.setName(question.getName())
                            .setQuestionType(question.getQuestionType())
                            .setDescription(question.getDescription())
                            .setScore(question.getScore());
                }
            }
        }
        return questionViewerList;
    }

    public List<HistoryExamViewer> transform2(List<QuestionViewer> questionViewerList, List<HistoryExamQuestion> historyExamQuestionList){
        List<HistoryExamViewer> list=new ArrayList<>();
        for (QuestionViewer questionViewer : questionViewerList) {
            for (HistoryExamQuestion historyExamQuestion : historyExamQuestionList) {
                if(questionViewer.getId().equals(historyExamQuestion.getQuestionId())){
                    HistoryExamViewer historyExamViewer = BeanUtil.copyProperties(questionViewer, HistoryExamViewer.class);
                    historyExamViewer.setCorrect(historyExamQuestion.getCorrect()==1)
                            .setAnswer(historyExamQuestion.getAnswer());
                    list.add(historyExamViewer);
                }
            }
        }
        return list;
    }
}