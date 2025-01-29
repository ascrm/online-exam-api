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
 * 判断题表 实体类。
 *
 * @author ascrm
 * @since 1.0
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "tb_judge_question")
public class JudgeQuestion {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "question_id")
    private Integer questionId;

    @Column(value = "answer")
    private String answer;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    @Column(value = "is_delete")
    private Integer isDelete;


}
