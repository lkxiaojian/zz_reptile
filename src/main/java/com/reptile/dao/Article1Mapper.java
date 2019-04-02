package com.reptile.dao;

import com.reptile.entity.Article1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Article1Mapper {
    int insert(Article1 record);

    List<Article1> selectAll();

    int getWxDataCount(Map<String, Object> map);

    List<Map<String, Object>> getWxData(Map<String, Object> paremMap);

    List<Map<String, Object>> ArithmeticArticle(Map<String, Object> paremMap);
    List<Map<String, Object>> ArithmeticArticle1(Map<String, Object> paramMap);
}