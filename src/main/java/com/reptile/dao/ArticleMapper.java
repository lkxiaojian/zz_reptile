package com.reptile.dao;

import com.reptile.entity.Article;
import com.reptile.entity.ArticleExample;
import com.reptile.entity.ArticleWithBLOBs;

import java.util.List;
import java.util.Map;

import com.reptile.entity.ReptileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

    List<ArticleWithBLOBs> selectaData(ArticleExample example);
    List<ArticleWithBLOBs> selectArticleData(ArticleExample example);

    List<ArticleWithBLOBs> selArticleData(ReptileEntity record);


    List<Article> selectByExample(ArticleExample example);

    List<Article>  getArticleTitle(Map<String, Object> map);
    int setGetStartAdd(@Param("articleId") String articleId);


    int updateDataState(List<String> list);


    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByDetails(@Param("record") ArticleWithBLOBs record);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);


    int getWxDataCount(Map<String, Object> map);

    List<Article> getWxData(Map<String, Object> paremMap);
}
