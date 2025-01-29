package com.ascrm.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: ascrm
 * @Date: 2025/1/29
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Integer id;
    private Integer examPaperId;
    private String name;
    private Integer questionType;
    private String description;
    private BigDecimal score;
    private String analysis;
    private Integer difficulty;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDeleted;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
