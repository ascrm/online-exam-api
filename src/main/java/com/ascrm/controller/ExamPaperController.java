package com.ascrm.controller;

import com.ascrm.converter.ExamPaperConverter;
import com.ascrm.entity.*;
import com.ascrm.service.ExamPaperService;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.service.HistoryExamService;
import com.ascrm.service.impl.ExamQuestionServiceImpl;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.ExamPaperViewer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.ascrm.entity.table.ExamPaperTableDef.EXAM_PAPER;
import static com.ascrm.entity.table.ExamQuestionTableDef.EXAM_QUESTION;
import static com.ascrm.entity.table.HistoryExamTableDef.HISTORY_EXAM;

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

    private final HistoryExamService historyExamService;

    private final ExamQuestionService examQuestionService;

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
        List<ExamQuestion> list = examQuestionService.list(new QueryWrapper().where(EXAM_QUESTION.EXAM_PAPER_ID.eq(examPaper.getId())));
        if(CollectionUtils.isEmpty(list) && examPaper.getIsPublished()==1) return Result.fail("无法发布白卷");
        examPaperService.updateById(examPaper);
        return Result.success();
    }

    /**
     * 删除试卷
     */
    @DeleteMapping("/examPaper")
    public Result<String> deleteExamPaper(int id){
        examPaperService.updateChain()
                .set(EXAM_PAPER.IS_DELETE,true)
                .where(EXAM_PAPER.ID.eq(id))
                .update();
        return Result.success();
    }

    /**
     * 批量删除试卷
     */
    @DeleteMapping("/examPapers")
    public Result<String> deleteExamPapers(String ids){
        examPaperService.updateChain()
                .set(EXAM_PAPER.IS_DELETE, true)
                .where(EXAM_PAPER.ID.in(Arrays.asList(ids.split(","))))
                .update();
       return Result.success();
    }

    /**
     * 条件查询
     */
    @PostMapping("/examPaper/condition")
    public Result<List<ExamPaperViewer>> getExamPaperByCondition(@RequestBody ExamPaper examPaper){
        List<ExamPaper> examPaperList = examPaperService.queryChain()
                .select(EXAM_PAPER.ALL_COLUMNS)
                .from(EXAM_PAPER)
                .where(EXAM_PAPER.IS_DELETE.eq(0))
                .and(EXAM_PAPER.NAME.eq(examPaper.getName()))
                .and(EXAM_PAPER.IS_PUBLISHED.eq(examPaper.getIsPublished()))
                .list();
        return Result.success(examPaperConverter.to(examPaperList));
    }

    /**
     * 查询用户考试列表
     */
    @GetMapping("/examPaper/userExamList")
    public Result<List<ExamPaperViewer>> getUserExamList(){
        List<HistoryExam> historyExamList = historyExamService.queryChain().select(HISTORY_EXAM.ALL_COLUMNS)
                .from(HISTORY_EXAM)
                .where(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername()))
                .and(HISTORY_EXAM.IS_DELETE.eq(0))
                .list();
        List<Integer> ids = historyExamList.stream().map(HistoryExam::getExamPaperId).toList();
        List<ExamPaper> examPaperList = examPaperService.queryChain()
                .select(EXAM_PAPER.ALL_COLUMNS)
                .from(EXAM_PAPER)
                .where(EXAM_PAPER.IS_DELETE.eq(0))
                .and(EXAM_PAPER.IS_PUBLISHED.eq(1))
                .and(EXAM_PAPER.ID.notIn(ids))
                .list();
        return Result.success(examPaperConverter.to(examPaperList));
    }
}