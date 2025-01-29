package com.ascrm.handler;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.JudgeQuestion;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.mapper.JudgeQuestionMapper;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.ascrm.entity.table.JudgeQuestionTableDef.JUDGE_QUESTION;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
@RequiredArgsConstructor
public class JudgeQuestionHandler implements QuestionHandler{

    private final JudgeQuestionMapper judgeQuestionMapper;

    @Override
    public QuestionTypeEnum getQuestionTypeEnum() {
        return QuestionTypeEnum.JUDGE;
    }

    @Override
    public void addQuestion(QuestionDTO questionDTO) {
        JudgeQuestion judgeQuestion = BeanUtil.copyProperties(questionDTO, JudgeQuestion.class);
        judgeQuestionMapper.insert(judgeQuestion,true);
    }

    @Override
    public void updateQuestion(QuestionDTO questionDTO) {
        JudgeQuestion judgeQuestion = BeanUtil.copyProperties(questionDTO, JudgeQuestion.class);
        judgeQuestionMapper.updateByQuery(judgeQuestion,new QueryWrapper()
                .where(JUDGE_QUESTION.QUESTION_ID.eq(questionDTO.getId())));
    }

    @Override
    public void deleteQuestion(Integer id) {
        UpdateChain.of(JudgeQuestion.class)
                .from(JUDGE_QUESTION)
                .set(JUDGE_QUESTION.IS_DELETE, true)
                .where(JUDGE_QUESTION.QUESTION_ID.eq(id))
                .update();
    }

    @Override
    public void deleteQuestions(String ids) {
        UpdateChain.of(JudgeQuestion.class)
                .from(JUDGE_QUESTION)
                .set(JUDGE_QUESTION.IS_DELETE, true)
                .where(JUDGE_QUESTION.QUESTION_ID.in(Arrays.asList(ids.split(","))))
                .update();
    }

    @Override
    public QuestionViewer getQuestionViewerById(QuestionViewer questionViewer) {
        JudgeQuestion judgeQuestion = QueryChain.of(JudgeQuestion.class)
                .select(JUDGE_QUESTION.ALL_COLUMNS)
                .from(JUDGE_QUESTION)
                .where(JUDGE_QUESTION.QUESTION_ID.eq(questionViewer.getId()))
                .one();
        questionViewer.setAnswer(judgeQuestion.getAnswer());
        return questionViewer;
    }
}
