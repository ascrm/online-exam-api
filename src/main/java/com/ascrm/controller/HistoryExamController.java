package com.ascrm.controller;

import com.ascrm.entity.ExamQuestion;
import com.ascrm.entity.HistoryExam;
import com.ascrm.entity.HistoryExamQuestion;
import com.ascrm.entity.Result;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.service.HistoryExamQuestionService;
import com.ascrm.service.HistoryExamService;
import com.ascrm.utils.UserHolder;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        if(historyExamResult!=null) return Result.fail("");
        historyExamService.save(historyExam);

        List<ExamQuestion> examQuestionList = examQuestionService.list(new QueryWrapper()
                .where(EXAM_QUESTION.EXAM_PAPER_ID.eq(historyExam.getExamPaperId()))
                .and(EXAM_QUESTION.IS_DELETE.eq(0)));
        if(CollectionUtils.isEmpty(examQuestionList)) return Result.fail("");
        List<HistoryExamQuestion> historyExamQuestionList = examQuestionList.stream().map(examQuestion -> {
            HistoryExamQuestion historyExamQuestion = new HistoryExamQuestion();
            return historyExamQuestion.setCorrect(0).setQuestionId(examQuestion.getId()).setHistoryExamId(historyExam.getId());
        }).toList();
        historyExamQuestionService.saveBatch(historyExamQuestionList);
        return Result.success();
    }
}