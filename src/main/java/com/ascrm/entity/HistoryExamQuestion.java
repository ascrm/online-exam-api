package com.ascrm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.String;
import java.lang.Integer;

/**
 * 实体类。
 *
 * @author ascrm
 * @since 1.0
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "tb_history_exam_question")
public class HistoryExamQuestion {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "history_exam_id")
    private Integer historyExamId;

    @Column(value = "question_id")
    private Integer questionId;

    @Column(value = "answer")
    private String answer;

    /**
     * 1正确  0错误
     */
    @Column(value = "correct")
    private Integer correct;

    @Column(value = "is_delete")
    private Integer isDelete;


}
