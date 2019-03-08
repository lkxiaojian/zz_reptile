package com.reptile.dao;

import com.reptile.entity.ArticleType;
import com.reptile.entity.ArticleTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer articleTypeId);

    int deleteById(String articleId);


    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    List<ArticleType> selectByExample(ArticleTypeExample example);

    List<ArticleType> selectAllKeyWork(@Param("rem") Integer rem);


    List<ArticleType> selectKeyWork(@Param("day") Integer day,@Param("threadNum") Integer threadNum,@Param("rem") Integer rem);

    int updateLastTime( ArticleType articleType);


    ArticleType selectByPrimaryKey(Integer articleTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}
