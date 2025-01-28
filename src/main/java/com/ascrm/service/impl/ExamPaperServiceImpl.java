package com.ascrm.service.impl;


import org.springframework.stereotype.Service;
import com.ascrm.service.ExamPaperService;
import com.ascrm.entity.ExamPaper;
import com.ascrm.mapper.ExamPaperMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 服务层实现。
 *
 * @author ascrm
 * @since 1.0
 */
@Service
public class ExamPaperServiceImpl extends ServiceImpl<ExamPaperMapper, ExamPaper> implements ExamPaperService {

}