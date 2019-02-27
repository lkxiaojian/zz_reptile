package com.reptile.task;

import com.alibaba.fastjson.JSON;
import com.reptile.dao.AcademicPaperMapper;
import com.reptile.dao.Article1Mapper;
import com.reptile.entity.AbstractTmp;
import com.reptile.entity.ArticleTmp;
import com.reptile.properties.RasterProperties;
import com.reptile.utlils.HttpRequest;
import com.reptile.utlils.HttpUpload;
import com.reptile.utlils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class ArithmeticArticleTask {

    private static final Logger log = LoggerFactory.getLogger(ArithmeticArticleTask.class);

    @Resource
    private Article1Mapper article1Mapper;
    @Resource
    private RasterProperties rasterProperties;

    @Resource
    private AcademicPaperMapper academicPaperMapper;

    @Value("${articlePath}")
    private String articlePath;

    @Value("${abstractPath}")
    private String abstractPath;

    @Value("${postPath}")
    private String postPath;

    private int rows = 10;

    private HttpUpload upFile = new HttpUpload();


//        @Scheduled(cron = "0/30 * * * * ?")
    public void ArithmeticArticle() {
        int page = Integer.parseInt(rasterProperties.getPropValue("1"));
        Map<String, Object> paremMap = new HashMap<>();
        paremMap.put("rows", rows);
        paremMap.put("page", page * rows);
        List<Map<String, Object>> maps = article1Mapper.ArithmeticArticle(paremMap);
        List<Map<String, Object>> abstractMaps = new ArrayList<>();
        List<Map<String, Object>> typeMaps = new ArrayList<>();
        Map<String, Object> typeMap = null;
        Map<String, Object> abstractMap = null;

        try {
            for (int i = 0; i < maps.size(); i++) {
                abstractMaps = new ArrayList<>();
                typeMaps = new ArrayList<>();
                typeMap = new HashMap<>();
                abstractMap = new HashMap<>();
                byte[] details_divbytes = (byte[]) maps.get(i).get("details_txt");
                typeMap.put("content", new String(details_divbytes, "UTF-8"));
                typeMap.put("article_id", maps.get(i).get("article_id"));
                typeMap.put("title", maps.get(i).get("article_title"));
                typeMaps.add(typeMap);
                abstractMap.put("doc", new String(details_divbytes, "UTF-8"));
                abstractMap.put("id", maps.get(i).get("article_id"));
                abstractMap.put("link", new String((byte[]) maps.get(i).get("details_div"), "UTF-8"));
                abstractMaps.add(abstractMap);

                //类型
                String type = JSON.toJSONString(typeMaps);
                if (type.length() > 2) {
                    type = "{ \"articles\": [" + type.substring(1, type.length() - 1) + "    ]}";
                }
                String sendTypePost = HttpUtils.doPost(articlePath + "wechat", type);
                if (sendTypePost.isEmpty()) {
                    break;
                }
                ArticleTmp article_tmp = JSON.parseObject(sendTypePost, ArticleTmp.class);
                String abstracts = JSON.toJSONString(abstractMaps);
                if (abstracts.isEmpty()) {
                    break;
                }
                //摘要
                String sendAbstractsPost = HttpUtils.doPost(abstractPath + "abstract/", abstracts);
                List<AbstractTmp> abstractTmp = JSON.parseArray(sendAbstractsPost, AbstractTmp.class);


                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("article_id", maps.get(i).get("article_id"));
                resultMap.put("article_keywords", article_tmp.getResult().get(0).getArticle_keywords());
                resultMap.put("type_id", article_tmp.getResult().get(0).getType_id());
                resultMap.put("type_name", article_tmp.getResult().get(0).getType_name());
                resultMap.put("article_txt", new String(details_divbytes, "UTF-8"));
                resultMap.put("article_div", new String((byte[]) maps.get(i).get("details_div"), "UTF-8"));
                resultMap.put("article_title", maps.get(i).get("article_title"));
                resultMap.put("summary", abstractTmp.get(0).getSummary());
                resultMap.put("create_time", maps.get(i).get("create_time").toString().substring(0, 19));
                resultMap.put("author", maps.get(i).get("author"));
                resultMap.put("source", maps.get(i).get("source"));
                String s = HttpUtils.doPost(postPath + "algorithm/wxdata", JSON.toJSONString(resultMap));


                rasterProperties.setProp("1", (page + 1) + "");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    @Scheduled(cron = "0/30 * * * * ?")
    public void arithmeticPapar() {
        int page = Integer.parseInt(rasterProperties.getPropValue("2"));
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("rows", rows);
        parmMap.put("page", page * rows);
        List<Map<String, Object>> paperMaps = new ArrayList<>();
        Map<String, Object> paperMap = null;
        List<Map<String, Object>> maps = academicPaperMapper.ArithmeticPaperTmp(parmMap);
        for (int i = 0; i < maps.size(); i++) {
            paperMaps = new ArrayList<>();
            paperMap = new HashMap<>();
            paperMap.put("article_id", maps.get(i).get("article_id"));
            paperMap.put("title", maps.get(i).get("article_title"));
            paperMap.put("content", maps.get(i).get("content_excerpt"));
            paperMaps.add(paperMap);

            //类型
            String type = JSON.toJSONString(paperMaps);
            if (type.length() > 2) {
                type = "{ \"articles\": [" + type.substring(1, type.length() - 1) + "    ]}";
            }

            String sendTypePost = HttpUtils.doPost(articlePath + "paper", type);
            if (sendTypePost.isEmpty()) {
                break;
            }
//            ArticleTmp article_tmp = JSON.parseObject(sendTypePost, ArticleTmp.class);

            String pdf_path = "D:/File/" + maps.get(i).get("pdf_path");
            pdf_path = "E:/Users/lk/springboot/zz_reptiles/reptiles/pom.xml";

            Map<String, Object> map = maps.get(i);
// article_title,article_id ,article_keyword,author,create_time,source,content_excerpt,
// image_path,posting_name,article_title_e,content_excerpt_e,
// pdf_path,article_keyword_e,author_e,reference,site_number,seach_keyword,publication_date
            File outFile = new File(pdf_path);
            boolean exists = outFile.exists();
            Map<String, String> param = new HashMap<String, String>();
            param.put("FILE_PATH", outFile.getAbsolutePath());
            param.put("FILE_NAME", outFile.getName());
            param.put("article_title", map.get("article_title").toString());
            param.put("article_id", map.get("article_id").toString());
            param.put("article_keyword", map.get("article_keyword").toString());
            param.put("author", map.get("author")==null? "": map.get("author").toString());
            param.put("create_time", map.get("create_time")==null? "": map.get("create_time").toString());
            param.put("source", map.get("source")==null? "": map.get("source").toString());
            param.put("content_excerpt", map.get("content_excerpt")==null? "": map.get("content_excerpt").toString());
            param.put("image_path", map.get("image_path")==null? "": map.get("image_path").toString());
            param.put("posting_name", map.get("posting_name")==null? "": map.get("posting_name").toString());
            param.put("article_title_e", map.get("article_title_e")==null? "": map.get("article_title_e").toString());
            param.put("content_excerpt_e", map.get("content_excerpt_e")==null? "": map.get("content_excerpt_e").toString());
            param.put("article_keyword_e", map.get("article_keyword_e")==null? "": map.get("article_keyword_e").toString());
            param.put("author_e", map.get("author_e")==null? "": map.get("author_e").toString());
            param.put("reference", map.get("reference")==null? "": map.get("reference").toString());
            param.put("site_number", map.get("site_number")==null? "": map.get("site_number").toString());
            param.put("seach_keyword", map.get("seach_keyword")==null? "": map.get("seach_keyword").toString());
            param.put("publication_date", map.get("publication_date")==null? "": map.get("publication_date").toString());


            param.put("json",sendTypePost);
            String uplaod = upFile.uplaod(postPath + "weatherData/fileUpload", param);

            rasterProperties.setProp("2", (page + 1) + "");
        }

    }


}
