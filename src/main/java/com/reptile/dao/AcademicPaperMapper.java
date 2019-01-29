package com.reptile.dao;

import com.reptile.entity.AcademicPaper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface AcademicPaperMapper {
    int insert(AcademicPaper record);

    List<AcademicPaper> selectAll();

    List<Map<String,Object>> getData(Map<String, Object> map);

    int getCountByName(Map<String, Object> map);
}