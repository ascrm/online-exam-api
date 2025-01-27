package com.ascrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: ascrm
 * @Date: 2025/1/28
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> list;
}
