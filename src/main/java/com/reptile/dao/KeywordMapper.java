package com.reptile.dao;

import com.reptile.entity.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordMapper {
    int insert(Keyword record);
    int insertKeywordList(List<Keyword> record);
    List<Keyword> selectAll();

    int updateKeyword(Keyword keyword);

    int delKeyword(String id);

    int updateDelKeyword(Keyword record);
}