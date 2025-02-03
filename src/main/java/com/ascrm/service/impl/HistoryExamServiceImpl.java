package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.HistoryExamService;
import com.ascrm.entity.HistoryExam;
import com.ascrm.mapper.HistoryExamMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class HistoryExamServiceImpl extends ServiceImpl<HistoryExamMapper, HistoryExam> implements HistoryExamService {

}