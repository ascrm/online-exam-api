package com.ascrm.converter;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.Question;
import com.ascrm.viewer.QuestionViewer;
import org.springframework.stereotype.Component;
import com.ascrm.enums.QuestionTypeEnum;
import com.ascrm.enums.QuestionDifficultyEnum;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
public class QuestionConverter {

    public List<QuestionViewer> to(List<Question> questionList){
        List<QuestionViewer> list=new ArrayList<>();
        questionList.forEach(question -> list.add(to(question)));
        return list;
    }

    public QuestionViewer to(Question question){
        QuestionViewer questionViewer = BeanUtil.copyProperties(question, QuestionViewer.class);
        if(questionViewer.getQuestionType()!=null) questionViewer.setQuestionTypeLabel(Objects.requireNonNull(QuestionTypeEnum.getByCode(questionViewer.getQuestionType())).getLabel());
        if(questionViewer.getDifficulty()!=null) questionViewer.setDifficultyLabel(Objects.requireNonNull(QuestionDifficultyEnum.getByCode(Integer.valueOf(questionViewer.getDifficulty()))).getLabel());
        if(question.getCreatedAt()!=null) questionViewer.setCreatedAt(question.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return questionViewer;
    }
}
