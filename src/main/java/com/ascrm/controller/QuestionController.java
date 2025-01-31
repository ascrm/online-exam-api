package com.ascrm.controller;

import com.ascrm.converter.QuestionConverter;
import com.ascrm.entity.*;
import com.ascrm.entity.DTO.QuestionDTO;
import com.ascrm.service.QuestionService;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ascrm.entity.table.QuestionTableDef.QUESTION;

/**
 * 控制层。
 *
 * @author ascrm
 * @since 1.0
 */
@RestController
@RequestMapping("/online/exam")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    private final QuestionConverter questionConverter;

    /**
     * 分页查询题目列表
     */
    @GetMapping("/questions")
    public Result<PageResult<QuestionViewer>> getExamPaperList(@RequestParam("pageNum") int pageNum,
                                                               @RequestParam("pageSize") int pageSize) {
        Page<Question> page = questionService.page(new Page<>(pageNum, pageSize), new QueryWrapper()
                .where(QUESTION.IS_DELETED.eq(0))
                .orderBy(QUESTION.QUESTION_TYPE.asc())
                .orderBy(QUESTION.CREATED_AT.desc()));
        PageResult<QuestionViewer> pageResult = new PageResult<>();
        pageResult.setPageNum(pageNum)
                .setPageSize(pageSize)
                .setTotal(page.getTotalRow())
                .setList(questionConverter.to(page.getRecords()));
        return Result.success(pageResult);
    }

    /**
     * 新增题目
     */
    @PostMapping("/question")
    public Result<String> addQuestion(@RequestBody QuestionDTO questionDTO) {
        questionService.addQuestion(questionDTO);
        return Result.success();
    }

    /**
     * 修改题目信息
     */
    @PutMapping("/question")
    public Result<String> updateQuestion(@RequestBody QuestionDTO questionDTO){
        questionService.updateQuestion(questionDTO);
        return Result.success();
    }

    /**
     * 删除题目
     */
    @DeleteMapping("/question")
    public Result<String> deleteQuestion(int id){
        questionService.deleteQuestion(id);
        return Result.success();
    }

    /**
     * 批量删除题目
     */
    @DeleteMapping("/questions")
    public Result<String> deleteQuestions(String ids){
        questionService.deleteQuestions(ids);
        return Result.success();
    }

    /**
     * 根据条件获取题目列表
     */
    @PostMapping("/questions/condition")
    public Result<List<QuestionViewer>> getQuestionsByCondition(@RequestBody Question question){
        if(question.getQuestionType()==null){
            return Result.success(questionConverter.to(questionService.list()));
        }
        List<Question> list = questionService.queryChain()
                .select(QUESTION.ALL_COLUMNS)
                .from(QUESTION)
                .where(QUESTION.IS_DELETED.eq(0))
                .and(QUESTION.NAME.eq(question.getName()))
                .and(QUESTION.QUESTION_TYPE.eq(question.getQuestionType()))
                .and(QUESTION.DIFFICULTY.eq(question.getDifficulty()))
                .list();
        return Result.success(questionConverter.to(list));
    }

    /**
     * 根据题目id获取题目详细信息
     */
    @GetMapping("/question")
    public Result<QuestionViewer> getQuestionViewerById(int id){
        return Result.success(questionService.getQuestionViewerById(id));
    }
}