package com.ascrm.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
@Table(value = "tb_history_exam")
public class HistoryExam {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    @Column(value = "username")
    private String username;

    @Column(value = "exam_paper_id")
    private Integer examPaperId;

    @Column(value = "is_delete")
    private Integer isDelete;


}
