package com.ascrm.controller;

import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.entity.ExamQuestion;
import com.ascrm.entity.Result;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ascrm.entity.table.ExamPaperTableDef.EXAM_PAPER;
import static com.ascrm.entity.table.ExamQuestionTableDef.EXAM_QUESTION;

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
    @PostMapping("/exam/question")
    public Result<String> importQuestion(@RequestBody QuestionDTO questionDTO) {
        ExamQuestion examQuestion = new ExamQuestion();

        ExamQuestion examQuestionResult = examQuestionService.getOne(new QueryWrapper().
                where(EXAM_QUESTION.EXAM_PAPER_ID.eq(questionDTO.getExamPaperId()))
                .and(EXAM_QUESTION.QUESTION_ID.eq(questionDTO.getId())));

        if(examQuestionResult!=null) return Result.fail("不能重复导入");
        
        examQuestion.setQuestionId(questionDTO.getId())
                .setExamPaperId(questionDTO.getExamPaperId());
        examQuestionService.save(examQuestion);
        return Result.success();
    }

    /**
     * 根据试卷id和题目分类
     * 获取当前试卷下某个类别的所有的题目信息（包括详细信息）
     */
    @GetMapping("/exam/question")
    public Result<List<QuestionViewer>> getQuestionViewerByExamPaperIdAndQuestionType(int examPaperId,int questionType) {
        return Result.success(examQuestionService.getQuestionViewerByExamPaperIdAndQuestionType(examPaperId,questionType));
    }

    /**
     * 删除添加的题目
     */
    @DeleteMapping("/exam/question")
    public Result<String> deleteExamQuestionByExamPaperIdAndQuestionId(int examPaperId,int questionId){
        UpdateChain.of(ExamQuestion.class)
                .from(EXAM_QUESTION)
                .set(EXAM_QUESTION.IS_DELETE, true)
                .where(EXAM_QUESTION.EXAM_PAPER_ID.eq(examPaperId))
                .and(EXAM_QUESTION.QUESTION_ID.eq(questionId))
                .update();
        return Result.success();
    }
}