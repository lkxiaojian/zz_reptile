package com.reptile.dao;

import com.reptile.entity.AcademicPaper;
import java.util.List;

public interface AcademicPaperMapper {
    int insert(AcademicPaper record);

    List<AcademicPaper> selectAll();
}