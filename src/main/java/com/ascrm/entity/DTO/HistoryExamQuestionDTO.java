package com.ascrm.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: ascrm
 * @Date: 2025/2/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HistoryExamQuestionDTO {
    private Integer examPaperId;
    private Integer questionType;
    private Integer questionId;
    private String answer;
}
