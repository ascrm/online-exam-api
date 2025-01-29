package com.ascrm.handler;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.MultipleChoiceQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.MultipleChoiceQuestionMapper;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.ascrm.entity.table.MultipleChoiceQuestionTableDef.MULTIPLE_CHOICE_QUESTION;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
@RequiredArgsConstructor
public class MultipleQuestionHandler implements QuestionHandler{

    private final MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.MULTIPLE_CHOICE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        MultipleChoiceQuestion multipleChoiceQuestion = BeanUtil.copyProperties(questionDTO, MultipleChoiceQuestion.class);
        multipleChoiceQuestionMapper.insert(multipleChoiceQuestion,true);
    }

    @Override
    public void updateQuestion(QuestionDTO questionDTO) {
        MultipleChoiceQuestion multipleChoiceQuestion = BeanUtil.copyProperties(questionDTO, MultipleChoiceQuestion.class);
        multipleChoiceQuestionMapper.updateByQuery(multipleChoiceQuestion,new QueryWrapper()
                .where(MULTIPLE_CHOICE_QUESTION.QUESTION_ID.eq(questionDTO.getId())));
    }

    @Override
    public void deleteQuestion(Integer id) {
        UpdateChain.of(MultipleChoiceQuestion.class)
                .from(MULTIPLE_CHOICE_QUESTION)
                .set(MULTIPLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(MULTIPLE_CHOICE_QUESTION.QUESTION_ID.eq(id))
                .update();
    }

    @Override
    public void deleteQuestions(String ids) {
        UpdateChain.of(MultipleChoiceQuestion.class)
                .from(MULTIPLE_CHOICE_QUESTION)
                .set(MULTIPLE_CHOICE_QUESTION.IS_DELETE, true)
                .where(MULTIPLE_CHOICE_QUESTION.QUESTION_ID.in(Arrays.asList(ids.split(","))))
                .update();
    }

    @Override
    public QuestionViewer getQuestionViewerById(QuestionViewer questionViewer) {
        MultipleChoiceQuestion multipleChoiceQuestion = QueryChain.of(MultipleChoiceQuestion.class)
                .select(MULTIPLE_CHOICE_QUESTION.ALL_COLUMNS)
                .from(MULTIPLE_CHOICE_QUESTION)
                .where(MULTIPLE_CHOICE_QUESTION.QUESTION_ID.eq(questionViewer.getId()))
                .one();
        questionViewer.setOptionA(multipleChoiceQuestion.getOptionA())
                .setOptionB(multipleChoiceQuestion.getOptionB())
                .setOptionC(multipleChoiceQuestion.getOptionC())
                .setOptionD(multipleChoiceQuestion.getOptionD())
                .setAnswer(multipleChoiceQuestion.getAnswer());
        return questionViewer;
    }
}
