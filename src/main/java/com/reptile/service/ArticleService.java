package com.reptile.service;

import java.util.Map;

public interface ArticleService {
    Map<String,Object> addKeyword(Map<String, Object> data) throws Exception;

    Map<String,Object> updateKeyword(String id, String keyword_name) throws  Exception;

    Map<String,Object> delKeyword(String id) throws  Exception;
}
