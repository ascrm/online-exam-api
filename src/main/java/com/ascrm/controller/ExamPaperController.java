package com.ascrm.controller;

import com.ascrm.converter.ExamPaperConverter;
import com.ascrm.entity.ExamPaper;
import com.ascrm.entity.PageResult;
import com.ascrm.entity.Result;
import com.ascrm.service.ExamPaperService;
import com.ascrm.viewer.ExamPaperViewer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.ascrm.entity.table.ExamPaperTableDef.EXAM_PAPER;

/**
 * 控制层。
 *
 * @author ascrm
 * @since 1.0
 */
@RestController
@RequestMapping("/online/exam")
@RequiredArgsConstructor
public class ExamPaperController {

    private final ExamPaperService examPaperService;

    private final ExamPaperConverter examPaperConverter;

    /**
     * 分页查询试卷列表
     */
    @GetMapping("/examPapers")
    public Result<PageResult<ExamPaperViewer>> getExamPaperList(@RequestParam("pageNum") int pageNum,
                                                          @RequestParam("pageSize") int pageSize) {
        Page<ExamPaper> page = examPaperService.page(new Page<>(pageNum, pageSize), new QueryWrapper()
                .where(EXAM_PAPER.IS_DELETE.eq(0))
                .orderBy(EXAM_PAPER.CREATED_AT.desc()));
        PageResult<ExamPaperViewer> pageResult = new PageResult<>();
        pageResult.setPageNum(pageNum)
                .setPageSize(pageSize)
                .setTotal(page.getTotalRow())
                .setList(examPaperConverter.to(page.getRecords()));
        return Result.success("查询成功",pageResult);
    }

    /**
     * 新增试卷
     */
    @PostMapping("/examPaper")
    public Result<String> addExamPaper(@RequestBody ExamPaper examPaper){
        examPaperService.save(examPaper);
        return Result.success();
    }

    /**
     * 修改试卷
     */
    @PutMapping("/examPaper")
    public Result<String> updateExamPaper(@RequestBody ExamPaper examPaper){
        examPaper.setUpdatedAt(LocalDateTime.now());
        examPaperService.updateById(examPaper);
        return Result.success();
    }

    /**
     * 删除试卷
     */
    @DeleteMapping("/examPaper/{id}")
    public Result<String> deleteExamPaper(@PathVariable int id){
        examPaperService.updateChain()
                .set(EXAM_PAPER.IS_DELETE,true)
                .where(EXAM_PAPER.ID.eq(id))
                .update();
        return Result.success();
    }
}