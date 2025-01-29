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
public class QuestionViewer {

    private Integer id;
    private Integer examPaperId;
    private String name;
    /**
     * 题目类型 1单选，2多选，3判断，4解答
     */
    private Integer questionType;
    private String questionTypeLabel;
    private String description;
    private BigDecimal score;
    private String analysis;
    /**
     * 1-5级难度
     */
    private Integer difficulty;
    private String difficultyLabel;
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private Integer isDeleted;


}
