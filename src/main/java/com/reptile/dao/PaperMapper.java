package com.reptile.dao;

import com.reptile.entity.Paper;
import com.reptile.entity.PaperExample;
import com.reptile.entity.PaperWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperMapper {
    int insert(PaperWithBLOBs record);

    int deleteByPaperId(String paperId);


    int insertSelective(PaperWithBLOBs record);

    List<PaperWithBLOBs> selectByExampleWithBLOBs(PaperExample example);

    List<Paper> selectByExample(PaperExample example);

    int updateByExampleSelective(@Param("record") PaperWithBLOBs record, @Param("example") PaperExample example);

    int updateByExampleWithBLOBs(@Param("record") PaperWithBLOBs record, @Param("example") PaperExample example);

    int updateByExample(@Param("record") Paper record, @Param("example") PaperExample example);
}
