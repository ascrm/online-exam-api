package com.ascrm.handler;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.SingleChoiceQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.SingleChoiceQuestionMapper;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.ascrm.entity.table.SingleChoiceQuestionTableDef.SINGLE_CHOICE_QUESTION;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
@RequiredArgsConstructor
public class SingleQuestionHandler implements QuestionHandler{

    private final SingleChoiceQuestionMapper singleChoiceQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.SINGLE_CHOICE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        SingleChoiceQuestion singleChoiceQuestion = BeanUtil.copyProperties(questionDTO, SingleChoiceQuestion.class);
        singleChoiceQuestionMapper.insert(singleChoiceQuestion,true);
    }

    @Override
    public void updateQuestion(QuestionDTO questionDTO) {
        SingleChoiceQuestion singleChoiceQuestion = BeanUtil.copyProperties(questionDTO, SingleChoiceQuestion.class);
        singleChoiceQuestionMapper.updateByQuery(singleChoiceQuestion, new QueryWrapper()
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.eq(questionDTO.getId())));
    }

    @Override
    public void deleteQuestion(Integer id) {
        UpdateChain.of(SingleChoiceQuestion.class)
                .from(SINGLE_CHOICE_QUESTION)
                .set(SINGLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.eq(id))
                .update();
    }

    @Override
    public void deleteQuestions(List<Integer> ids) {
        UpdateChain.of(SingleChoiceQuestion.class)
                .from(SINGLE_CHOICE_QUESTION)
                .set(SINGLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.in(ids))
                .update();
    }

    @Override
    public QuestionViewer getQuestionViewerById(QuestionViewer questionViewer) {
        SingleChoiceQuestion singleChoiceQuestion = QueryChain.of(SingleChoiceQuestion.class)
                .select(SINGLE_CHOICE_QUESTION.ALL_COLUMNS)
                .from(SINGLE_CHOICE_QUESTION)
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.eq(questionViewer.getId()))
                .one();
        questionViewer.setOptionA(singleChoiceQuestion.getOptionA())
                .setOptionB(singleChoiceQuestion.getOptionB())
                .setOptionC(singleChoiceQuestion.getOptionC())
                .setOptionD(singleChoiceQuestion.getOptionD())
                .setAnswer(singleChoiceQuestion.getAnswer());
        return questionViewer;
    }

    @Override
    public List<QuestionViewer> getQuestionViewerByIds(List<Integer> ids) {
        List<QuestionViewer> list = new ArrayList<>();
        if(CollectionUtils.isEmpty(ids)) ids=List.of(0);
        List<SingleChoiceQuestion> singleChoiceQuestions = singleChoiceQuestionMapper.selectListByQuery(new QueryWrapper()
                .where(SINGLE_CHOICE_QUESTION.QUESTION_ID.in(ids))
                .and(SINGLE_CHOICE_QUESTION.IS_DELETE.eq(0)));
        singleChoiceQuestions.forEach(singleChoiceQuestion -> {
            QuestionViewer questionViewer = BeanUtil.copyProperties(singleChoiceQuestion, QuestionViewer.class);
            questionViewer.setId(singleChoiceQuestion.getQuestionId());
            list.add(questionViewer);
        });
        return list;
    }
}
