package com.ascrm.viewer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author: ascrm
 * @Date: 2025/1/28
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaperViewer {

    private Integer id;
    private String name;
    private Integer duration;
    private BigDecimal totalScore;
    private BigDecimal passingScore;
    private String isPublished;
    private String isPublishedLabel;
    private String description;
    private String createdBy;
    private String createdAt;
    private String updatedAt;
    private Integer isDelete;
}
