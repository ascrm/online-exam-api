package com.ascrm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(value = "tb_exam_paper")
public class ExamPaper {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "name")
    private String name;

    @Column(value = "description")
    private String description;

    /**
     * 考试时长（分钟）
     */
    @Column(value = "duration")
    private Integer duration;

    @Column(value = "total_score")
    private BigDecimal totalScore;

    @Column(value = "passing_score")
    private BigDecimal passingScore;

    /**
     * 1发布 0未发布
     */
    @Column(value = "is_published")
    private Integer isPublished;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    @Column(value = "is_delete")
    private Integer isDelete;


}
