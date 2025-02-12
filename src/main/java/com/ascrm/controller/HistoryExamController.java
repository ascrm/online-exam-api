package com.ascrm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.*;
import com.ascrm.entity.DTO.HistoryExamQuestionDTO;
import com.ascrm.mapper.HistoryExamQuestionMapper;
import com.ascrm.service.ExamPaperService;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.service.HistoryExamQuestionService;
import com.ascrm.service.HistoryExamService;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.HistoryExamViewer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ascrm.entity.table.ExamPaperTableDef.EXAM_PAPER;
import static com.ascrm.entity.table.ExamQuestionTableDef.EXAM_QUESTION;
import static com.ascrm.entity.table.HistoryExamQuestionTableDef.HISTORY_EXAM_QUESTION;
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
public class HistoryExamController {

    private final HistoryExamService historyExamService;

    private final ExamQuestionService examQuestionService;

    private final HistoryExamQuestionService historyExamQuestionService;

    private final ExamPaperService examPaperService;

    /**
     * 创建历史记录
     */
    @PostMapping("/historyExam")
    public Result<String> addHistoryExam(@RequestBody HistoryExam historyExam) {
        historyExam.setUsername(UserHolder.getUsername());
        HistoryExam historyExamResult = historyExamService.getOne(new QueryWrapper()
                .where(HISTORY_EXAM.USERNAME.eq(historyExam.getUsername()))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.eq(historyExam.getExamPaperId()))
                .and(HISTORY_EXAM.IS_DELETE.eq(0)));
        if(historyExamResult!=null) return Result.success();
        historyExamService.save(historyExam);

        List<ExamQuestion> examQuestionList = examQuestionService.list(new QueryWrapper()
                .where(EXAM_QUESTION.EXAM_PAPER_ID.eq(historyExam.getExamPaperId()))
                .and(EXAM_QUESTION.IS_DELETE.eq(0)));
        if(CollectionUtils.isEmpty(examQuestionList)) return Result.success();
        List<HistoryExamQuestion> historyExamQuestionList = examQuestionList.stream().map(examQuestion -> {
            HistoryExamQuestion historyExamQuestion = new HistoryExamQuestion();
            return historyExamQuestion.setCorrect(0).setQuestionId(examQuestion.getQuestionId()).setHistoryExamId(historyExam.getId());
        }).toList();
        historyExamQuestionService.saveBatch(historyExamQuestionList);
        return Result.success();
    }

    /**
     * 提交答案
     */
    @PostMapping("/historyExamQuestion")
    public Result<String> updateHistoryExamQuestion(@RequestBody List<HistoryExamQuestionDTO> historyExamQuestionDTOList) {
        List<HistoryExamQuestion> list=new ArrayList<>();
        BigDecimal totalScore= BigDecimal.ZERO;

        for (HistoryExamQuestionDTO historyExamQuestionDTO : historyExamQuestionDTOList) {
            HistoryExamQuestion historyExamQuestion = BeanUtil.copyProperties(historyExamQuestionDTO, HistoryExamQuestion.class);
            if(historyExamQuestionDTO.getStandardAnswer().equals(historyExamQuestion.getAnswer())) {
                historyExamQuestion.setCorrect(1);
                totalScore = totalScore.add(historyExamQuestionDTO.getScore());
            }
            else historyExamQuestion.setCorrect(0);
            list.add(historyExamQuestion);
        }
        Db.executeBatch(list,100, HistoryExamQuestionMapper.class,(mapper,item)->
            mapper.updateByQuery(item,new QueryWrapper().where(HISTORY_EXAM_QUESTION.QUESTION_ID.eq(item.getQuestionId())
                    .and(HISTORY_EXAM_QUESTION.HISTORY_EXAM_ID.eq(item.getHistoryExamId()))))
        );

        historyExamService.updateChain().set(HISTORY_EXAM.TOTAL_SCORE,totalScore)
                .where(HISTORY_EXAM.EXAM_PAPER_ID.eq(historyExamQuestionDTOList.getFirst().getExamPaperId()))
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername()))
                .update();
        return Result.success();
    }

    /**
     * 查询单条历史记录
     */
    @GetMapping("/historyExamQuestion")
    public Result<HistoryExamQuestion> getHistoryExamQuestion(Integer examPaperId,Integer questionId) {
        HistoryExam historyExam = historyExamService.getOne(new QueryWrapper().where(HISTORY_EXAM.IS_DELETE.eq(0))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.eq(examPaperId))
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername())));
        HistoryExamQuestion historyExamQuestion = historyExamQuestionService.getOne(new QueryWrapper().where(HISTORY_EXAM_QUESTION.IS_DELETE.eq(0))
                .and(HISTORY_EXAM_QUESTION.HISTORY_EXAM_ID.eq(historyExam.getId()))
                .and(HISTORY_EXAM_QUESTION.QUESTION_ID.eq(questionId)));
        return Result.success(historyExamQuestion);
    }

    /**
     * 查询所有题目历史记录
     */
    @GetMapping("/historyExamQuestions")
    public Result<List<HistoryExamViewer>> getHistoryExamQuestionList(Integer examPaperId) {
        return Result.success(historyExamService.getHistoryExamQuestionList(examPaperId));
    }

    /**
     * 查询所有已经考完试的试卷记录(分页条件查询)
     */
    @GetMapping("/historyExams")
    public Result<PageResult<HistoryExamViewer>> getHistoryExamList(Integer pageNum,Integer pageSize,
                                                                    @RequestParam(value = "name",required = false) String examPaperName) {
        List<ExamPaper> examPaperList = examPaperService.list(new QueryWrapper().where(EXAM_PAPER.IS_DELETE.eq(0))
                .and(EXAM_PAPER.NAME.like(examPaperName)));
        List<Integer> ids = examPaperList.stream().map(ExamPaper::getId).toList();
        Page<HistoryExam> page = historyExamService.page(new Page<>(pageNum, pageSize), new QueryWrapper().where(HISTORY_EXAM.IS_DELETE.eq(0)
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername())))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.in(ids)));
        List<HistoryExamViewer> list = getHistoryExamViewers(page);
        PageResult<HistoryExamViewer> pageResult = new PageResult<>();
        pageResult.setPageSize(pageSize)
                .setPageNum(pageNum)
                .setTotal(page.getTotalRow())
                .setList(list);
        return Result.success(pageResult);
    }

    private @NotNull List<HistoryExamViewer> getHistoryExamViewers(Page<HistoryExam> page) {
        List<HistoryExam> historyExamList = page.getRecords();
        List<HistoryExamViewer> list=new ArrayList<>();
        historyExamList.forEach(historyExam -> {
            HistoryExamViewer historyExamViewer = new HistoryExamViewer();
            ExamPaper examPaper = examPaperService.getById(historyExam.getExamPaperId());
            historyExamViewer.setScore(historyExam.getTotalScore())
                    .setCreatedAt(historyExam.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setId(examPaper.getId())
                    .setName(examPaper.getName())
                    .setTotalScore(examPaper.getTotalScore())
                    .setPassingScore(examPaper.getPassingScore());
            list.add(historyExamViewer);
        });
        return list;
    }
}