package com.ascrm.controller;

import com.ascrm.entity.DTO.HistoryExamQuestionDTO;
import com.ascrm.entity.ExamQuestion;
import com.ascrm.entity.HistoryExam;
import com.ascrm.entity.HistoryExamQuestion;
import com.ascrm.entity.Result;
import com.ascrm.handler.QuestionHandler;
import com.ascrm.handler.QuestionHandlerFactory;
import com.ascrm.service.ExamQuestionService;
import com.ascrm.service.HistoryExamQuestionService;
import com.ascrm.service.HistoryExamService;
import com.ascrm.utils.UserHolder;
import com.ascrm.viewer.QuestionViewer;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    private final QuestionHandlerFactory questionHandlerFactory;

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
    public Result<String> updateHistoryExamQuestion(@RequestBody HistoryExamQuestionDTO historyExamQuestionDTO) {
        QuestionHandler handler = questionHandlerFactory.getHandler(historyExamQuestionDTO.getQuestionType());
        QuestionViewer questionViewer = handler.getQuestionViewerById(new QuestionViewer().setId(historyExamQuestionDTO.getQuestionId()));

        HistoryExam historyExam = historyExamService.getOne(new QueryWrapper().where(HISTORY_EXAM.IS_DELETE.eq(0))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.eq(historyExamQuestionDTO.getExamPaperId()))
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername())));

        historyExamQuestionService.updateChain()
                .where(HISTORY_EXAM_QUESTION.HISTORY_EXAM_ID.eq(historyExam.getId()))
                .and(HISTORY_EXAM_QUESTION.QUESTION_ID.eq(historyExamQuestionDTO.getQuestionId()))
                .set(HISTORY_EXAM_QUESTION.ANSWER, historyExamQuestionDTO.getAnswer())
                .set(HISTORY_EXAM_QUESTION.CORRECT, questionViewer.getAnswer().equals(historyExamQuestionDTO.getAnswer()) ? 1 : 0)
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
    public Result<List<HistoryExamQuestion>> getHistoryExamQuestionList(Integer examPaperId) {
        HistoryExam historyExam = historyExamService.getOne(new QueryWrapper().where(HISTORY_EXAM.IS_DELETE.eq(0))
                .and(HISTORY_EXAM.EXAM_PAPER_ID.eq(examPaperId))
                .and(HISTORY_EXAM.USERNAME.eq(UserHolder.getUsername())));
        List<HistoryExamQuestion> historyExamQuestionList = historyExamQuestionService.list(new QueryWrapper().where(HISTORY_EXAM_QUESTION.IS_DELETE.eq(0))
                .and(HISTORY_EXAM_QUESTION.HISTORY_EXAM_ID.eq(historyExam.getId())));
        return Result.success(historyExamQuestionList);
    }
}