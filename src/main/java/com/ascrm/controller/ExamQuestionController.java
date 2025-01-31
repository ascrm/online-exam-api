package com.ascrm.controller;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.ExamQuestion;
import com.ascrm.entity.Result;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.viewer.QuestionViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层。
 *
 * @author ascrm
 * @since 1.0
 */
@RestController
@RequestMapping("/online/exam")
@RequiredArgsConstructor
public class ExamQuestionController {

    private final ExamQuestionService examQuestionService;

    /**
     * 导入题目
     */
    @PostMapping("exam/question")
    public Result<String> importQuestion(@RequestBody QuestionDTO questionDTO) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setQuestionId(questionDTO.getId())
                .setExamPaperId(questionDTO.getExamPaperId());
        examQuestionService.save(examQuestion);
        return Result.success();
    }

    /**
     * 根据试卷id获取该试卷的题目信息（包括详细信息）
     */
    @GetMapping("/exam/question")
    public Result<List<QuestionViewer>> getQuestionViewerByExamPaperId(int examPaperId) {
        examQuestionService.getQuestionViewerByExamPaperId(examPaperId);
        return Result.success();
    }
}