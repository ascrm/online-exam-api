package com.ascrm.viewer;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
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
public class QuestionViewer {

    private Integer id;
    private String name;
    private Integer questionType;
    private String questionTypeLabel;
    private String description;
    private BigDecimal score;
    private String analysis;
    private String difficulty;
    private String difficultyLabel;
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private Integer isDeleted;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
