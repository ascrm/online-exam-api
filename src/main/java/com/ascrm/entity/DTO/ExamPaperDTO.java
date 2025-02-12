package com.ascrm.entity.DTO;

import com.ascrm.entity.PageParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: ascrm
 * @Date: 2025/2/12
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamPaperDTO extends PageParams {

    private Integer id;
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal totalScore;
    private BigDecimal passingScore;
    private Integer isPublished;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDelete;
}
