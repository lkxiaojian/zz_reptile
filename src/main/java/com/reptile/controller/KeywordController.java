package com.reptile.controller;

import com.reptile.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KeywordController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加关键字
     *
     * @return
     */
    @RequestMapping(value = "article/addKeyword", method = RequestMethod.GET)
    public Map<String, Object> addKeyword(String param, String uUid,String parent_id) {
        Map<String, Object> maps = null;
        if (param == null || "".equals(param) || uUid == null || "".equals(uUid)|| parent_id == null || "".equals(parent_id)) {
            return getErrorMap();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", param);
        map.put("uUid", uUid);
        map.put("parent_id", parent_id);

        try {
            maps = articleService.addKeyword(map);
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorMapService();
        }
        return maps;
    }


    /**
     * 更新关键字
     *
     * @return
     */
    @RequestMapping(value = "article/updateKeyword", method = RequestMethod.GET)
    public Map<String, Object> updateKeyword(String id, String keyword_name,String parent_id) {
        Map<String, Object> maps = null;
        if (keyword_name == null || "".equals(keyword_name) || id == null || "".equals(id) || parent_id == null || "".equals(parent_id)) {
            return getErrorMap();
        }

        try {
            maps = articleService.updateKeyword(id, keyword_name,parent_id);
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorMapService();
        }
        return maps;
    }


    /**
     * 更新关键字
     *
     * @return
     */
    @RequestMapping(value = "article/delKeyword", method = RequestMethod.GET)
    public Map<String, Object> delKeyword(String id) {
        Map<String, Object> maps = null;
        if (id == null || "".equals(id)) {
            return getErrorMap();
        }

        try {
            maps = articleService.delKeyword(id);
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorMapService();
        }
        return maps;
    }


    /**
     * 传参错误
     *
     * @return
     */
    private HashMap<String, Object> getErrorMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("message", "传参错误！");
        return map;
    }

    /**
     * 服务器内部错误
     *
     * @return
     */

    private HashMap<String, Object> getErrorMapService() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 2);
        map.put("message", "服务器内部错误！");
        return map;
    }


}
