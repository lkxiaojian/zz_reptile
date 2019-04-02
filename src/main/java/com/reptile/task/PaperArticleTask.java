package com.reptile.task;

import com.alibaba.fastjson.JSON;
import com.reptile.dao.AcademicPaperMapper;
import com.reptile.dao.Article1Mapper;
import com.reptile.properties.RasterProperties;
import com.reptile.utlils.HttpUpload;
import com.reptile.utlils.HttpUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PaperArticleTask
{
    private static final Logger log = LoggerFactory.getLogger(PaperArticleTask.class);


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
    private int rows = 50;

    private HttpUpload upFile = new HttpUpload();

    @Scheduled(cron="0/21 * * * * ?")
    public void arithmeticPapar()
    {
        try
        {
            int page = Integer.parseInt(this.rasterProperties.getPropValue("2"));
            Map parmMap = new HashMap();
            parmMap.put("rows", Integer.valueOf(this.rows));
            parmMap.put("page", Integer.valueOf(page * this.rows));
            List paperMaps = new ArrayList();
            Map paperMap = null;
            List maps = this.academicPaperMapper.ArithmeticPaperTmp(parmMap);
            for (int i = 0; i < maps.size(); i++) {
                paperMaps = new ArrayList();
                paperMap = new HashMap();
                paperMap.put("article_id", ((Map)maps.get(i)).get("article_id"));
                paperMap.put("title", ((Map)maps.get(i)).get("article_title"));
                paperMap.put("content", ((Map)maps.get(i)).get("content_excerpt"));
                paperMaps.add(paperMap);

                String type = JSON.toJSONString(paperMaps);
                if (type.length() > 2) {
                    type = "{ \"articles\": [" + type.substring(1, type.length() - 1) + "    ]}";
                }

                String sendTypePost = HttpUtils.doPost(this.articlePath + "paper", type);
                if (sendTypePost.isEmpty())
                {
                    break;
                }
                if (((Map)maps.get(i)).get("pdf_path") == null)
                {
                    break;
                }
                String pdf_path = "D:/File/" + ((Map)maps.get(i)).get("pdf_path").toString();

                Map map = (Map)maps.get(i);
                File outFile = new File(pdf_path);
                boolean exists = outFile.exists();
                System.out.print("论文id----->" + ((Map)maps.get(i)).get("article_id") + "\n");
                System.out.print("文件是否存在----->" + exists + "\n");
                Map param = new HashMap();
                param.put("FILE_PATH", outFile.getAbsolutePath());
                param.put("FILE_NAME", outFile.getName());
                param.put("article_title", map.get("article_title").toString());
                param.put("article_id", map.get("article_id").toString());
                param.put("article_keyword", map.get("article_keyword").toString());
                param.put("author", map.get("author") == null ? "" : map.get("author").toString());
                param.put("create_time", map.get("create_time") == null ? "" : map.get("create_time").toString());
                param.put("source", map.get("source") == null ? "" : map.get("source").toString());
                param.put("content_excerpt", map.get("content_excerpt") == null ? "" : map.get("content_excerpt").toString());
                param.put("image_path", map.get("image_path") == null ? "" : map.get("image_path").toString());
                param.put("posting_name", map.get("posting_name") == null ? "" : map.get("posting_name").toString());
                param.put("article_title_e", map.get("article_title_e") == null ? "" : map.get("article_title_e").toString());
                param.put("content_excerpt_e", map.get("content_excerpt_e") == null ? "" : map.get("content_excerpt_e").toString());
                param.put("article_keyword_e", map.get("article_keyword_e") == null ? "" : map.get("article_keyword_e").toString());
                param.put("author_e", map.get("author_e") == null ? "" : map.get("author_e").toString());
                param.put("reference", map.get("reference") == null ? "" : map.get("reference").toString());
                param.put("site_number", map.get("site_number") == null ? "" : map.get("site_number").toString());
                param.put("seach_keyword", map.get("seach_keyword") == null ? "" : map.get("seach_keyword").toString());
                param.put("publication_date", map.get("publication_date") == null ? "" : map.get("publication_date").toString());
                param.put("pdf_path", map.get("pdf_path") == null ? "" : map.get("pdf_path").toString());

                param.put("json", sendTypePost);
                String uplaod = this.upFile.uplaod(this.postPath + "weatherData/fileUpload", param);

                System.out.print("文件是否上传成功------->" + uplaod + "\n");
            }
            this.rasterProperties.setProp("2", page + 1 + "");
        } catch (Exception e) {
            System.out.print("文件上传失败" + e.toString() + "\n");
        }
    }


}