package com.ascrm.handler;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.JudgeQuestion;
import com.ascrm.entity.MultipleChoiceQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.MultipleChoiceQuestionMapper;
import com.mybatisflex.core.update.UpdateChain;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.ascrm.entity.table.JudgeQuestionTableDef.JUDGE_QUESTION;
import static com.ascrm.entity.table.MultipleChoiceQuestionTableDef.MULTIPLE_CHOICE_QUESTION;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
public class MultipleQuestionHandler implements QuestionHandler{

    @Resource
    private MultipleChoiceQuestionMapper multipleChoiceQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.MULTIPLE_CHOICE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setQuestionId(questionDTO.getId())
                .setAnswer(questionDTO.getAnswer())
                .setOptionA(questionDTO.getOptionA())
                .setOptionB(questionDTO.getOptionB())
                .setOptionC(questionDTO.getOptionC())
                .setOptionD(questionDTO.getOptionD())
                .setCreatedBy(questionDTO.getCreatedBy());
        multipleChoiceQuestionMapper.insert(multipleChoiceQuestion,true);
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
}
