package com.ascrm.entity;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.math.BigDecimal;
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
@Table(value = "tb_question")
public class Question {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "exam_paper_id")
    private Integer examPaperId;

    @Column(value="name")
    private String name;

    /**
     * 题目类型 1单选，2多选，3判断，4解答
     */
    @Column(value = "question_type")
    private Integer questionType;

    /**
     * 题目描述
     */
    @Column(value = "description")
    private String description;

    /**
     * 题目分数
     */
    @Column(value = "score")
    private BigDecimal score;

    /**
     * 题目解析
     */
    @Column(value = "analysis")
    private String analysis;

    /**
     * 1-5级难度
     */
    @Column(value = "difficulty")
    private Integer difficulty;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    @Column(value = "is_deleted")
    private Integer isDeleted;
}
