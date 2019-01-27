package com.reptile.service.iml;

import com.reptile.dao.KeywordMapper;
import com.reptile.entity.Keyword;
import com.reptile.service.ArticleService;
import com.reptile.utlils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleIml implements ArticleService {

    @Resource
    private KeywordMapper keywordMapper;

    @Override
    public Map<String, Object> addKeyword(Map<String, Object> data) throws Exception {
        Map<String,Object> map=new HashMap<>();
        String keyworads = data.get("keyword").toString();
        Date nowDate = DateUtil.getNowDate();
        Date dateyy = DateUtil.getDateyy("2017-01-01");

        List<Keyword> list = new ArrayList<>();
//        for (int i = 0; i < keyworads.length; i++) {
            Keyword keyword = new Keyword();
            keyword.setKeywordName(keyworads);
            keyword.setCreateTime(nowDate);
            keyword.setLastTime(dateyy);
            keyword.setId(data.get("uUid").toString());
            list.add(keyword);
//        }
        if (list.size() > 0) {
          keywordMapper.insertKeywordList(list);
        }

        map.put("code",0);
        map.put("message","成功");
        return map;
    }

    @Override
    public Map<String, Object> updateKeyword(String id, String keyword_name) throws Exception {
        Map<String,Object> map=new HashMap<>();
        Keyword keyword = new Keyword();
        keyword.setId(id);
        keyword.setKeywordName(keyword_name);
        keyword.setUpdateTime(new Date());
        int i = keywordMapper.updateKeyword(keyword);
        if(i==1){
            map.put("code",0);
            map.put("message","成功");
            return map;
        }
        map.put("code",1);
        return map;
    }

    @Override
    public Map<String, Object> delKeyword(String id) throws Exception {
        Map<String,Object> map=new HashMap<>();
      int count=  keywordMapper.delKeyword(id);
        if(count==1){
            map.put("code",0);
            map.put("message","成功");
            return map;
        }
        map.put("code",1);
        return map;
    }
}
