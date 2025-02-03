package com.ascrm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;

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
@Table(value = "tb_histoty_exam")
public class HistoryExam {

    @Column(value = "id")
    private Integer id;

    @Column(value = "user_id")
    private Integer userId;

    @Column(value = "exam_paper_id")
    private Integer examPaperId;

    @Column(value = "is_delete")
    private Integer isDelete;


}
