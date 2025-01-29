package com.ascrm.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.ascrm.enums.QuestionTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Component
@RequiredArgsConstructor
public class QuestionHandlerFactory implements InitializingBean {

    private final Map<QuestionTypeEnum,QuestionHandler> map=new HashMap<>();

    private final List<QuestionHandler> questionHandlerList;

    public QuestionHandler getHandler(int questionType){
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.getByCode(questionType);
        return map.get(questionTypeEnum);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (QuestionHandler handler : questionHandlerList) {
            map.put(handler.getQuestionTypeEnum(),handler);
        }
    }
}
