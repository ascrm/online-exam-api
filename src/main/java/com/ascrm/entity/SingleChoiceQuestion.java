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
import java.time.LocalDateTime;

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
@Table(value = "tb_single_choice_question")
public class SingleChoiceQuestion {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "question_id")
    private Integer questionId;

    @Column(value = "option_A")
    private String optionA;

    @Column(value = "option_B")
    private String optionB;

    @Column(value = "option_C")
    private String optionC;

    @Column(value = "option_D")
    private String optionD;

    /**
     * 正确答案
     */
    @Column(value = "answer")
    private String answer;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除
     */
    @Column(value = "is_delete")
    private Integer isDelete;
}
