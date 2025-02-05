package com.ascrm.viewer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author: ascrm
 * @Date: 2025/2/5
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryExamViewer {
    private Integer id;
    private String name;
    private Integer questionType;
    private String description;
    private BigDecimal score;
    private String createdBy;
    private String createdAt;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String answer;
    private String standardAnswer;
    private boolean correct;
    private Integer historyExamId;
}
