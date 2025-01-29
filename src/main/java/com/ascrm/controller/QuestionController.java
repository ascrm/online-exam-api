package com.ascrm.controller;

import com.ascrm.converter.QuestionConverter;
import com.ascrm.entity.*;
import com.ascrm.entity.QuestionViewer;
import com.ascrm.service.QuestionService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return Result.success("查询成功",pageResult);
    }
}