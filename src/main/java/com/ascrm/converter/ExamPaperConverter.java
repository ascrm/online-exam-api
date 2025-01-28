package com.ascrm.converter;

import cn.hutool.core.bean.BeanUtil;
import com.ascrm.entity.ExamPaper;
import com.ascrm.viewer.ExamPaperViewer;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ascrm
 * @Date: 2025/1/28
 */
@Component
public class ExamPaperConverter {

    public List<ExamPaperViewer> to(List<ExamPaper> examPaperList){
        List<ExamPaperViewer> list=new ArrayList<>();
        examPaperList.forEach(examPaper -> {
            ExamPaperViewer examPaperViewer = BeanUtil.copyProperties(examPaper, ExamPaperViewer.class);
            if(examPaper.getCreatedAt()!=null) examPaperViewer.setCreatedAt(examPaper.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if(examPaper.getUpdatedAt()!=null) examPaperViewer.setUpdatedAt(examPaper.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if(examPaper.getIsPublished()!=null) examPaperViewer.setIsPublishedLabel(examPaper.getIsPublished()==0?"未发布":"已发布");

            list.add(examPaperViewer);
        });
        return list;
    }
}
