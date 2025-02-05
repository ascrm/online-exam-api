package com.ascrm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.DTO.HistoryExamQuestionDTO;
import com.ascrm.entity.ExamQuestion;
import com.ascrm.entity.HistoryExam;
import com.ascrm.entity.HistoryExamQuestion;
import com.ascrm.entity.Result;
import com.ascrm.mapper.HistoryExamQuestionMapper;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.service.HistoryExamQuestionService;
import com.ascrm.service.HistoryExamService;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.HistoryExamViewer;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
     * 查询所有历史记录
     */
    @GetMapping("/historyExamQuestions")
    public Result<List<HistoryExamViewer>> getHistoryExamQuestionList(Integer examPaperId) {
        return Result.success(historyExamService.getHistoryExamQuestionList(examPaperId));
    }
}