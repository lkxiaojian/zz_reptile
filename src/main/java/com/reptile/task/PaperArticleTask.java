package com.reptile.task;

import com.alibaba.fastjson.JSON;
import com.reptile.dao.AcademicPaperMapper;
import com.reptile.dao.Article1Mapper;
import com.reptile.properties.RasterProperties;
import com.reptile.utlils.DateUtils;
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

//    @Scheduled(cron="0/21 * * * * ?")
@Scheduled(cron="0 15 1 ? * *")
//@Scheduled(initialDelay=100,fixedDelay=1000*60*5)
    public void arithmeticPapar()
    {
        try
        {
            int page =0;
//            int page = Integer.parseInt(this.rasterProperties.getPropValue("2"));
            Map parmMap = new HashMap();
            String s1 = DateUtils.dataSubtractOneDay(1);
            parmMap.put("rows", Integer.valueOf(this.rows));
//            parmMap.put("page", Integer.valueOf(page * this.rows));
            parmMap.put("startTime", s1+" 00:00:00");
            parmMap.put("endTime", s1+" 23:59:59");
            List paperMaps = new ArrayList();
            Map paperMap = null;
//            List maps = this.academicPaperMapper.ArithmeticPaperTmp(parmMap);
            List maps = this.academicPaperMapper.ArithmeticPaperTmpByTime(parmMap);
            for (int i = 0; i < maps.size(); i++) {
                paperMaps = new ArrayList();
                paperMap = new HashMap();
                Map dataMap = ((Map) maps.get(i));
                int is_english = 0;
                Object article_title = dataMap.get("article_title");
                Object content = dataMap.get("content_excerpt");
                if (article_title == null || article_title.toString().length() == 0) {
                    article_title = dataMap.get("article_title_e");
                    content = dataMap.get("content_excerpt_e");
                    is_english=1;
                }
                if (article_title == null || article_title.toString().length() == 0) {
                    continue;
                }



                paperMap.put("article_id", dataMap.get("article_id"));
                paperMap.put("title", article_title);
                paperMap.put("content", content);
                paperMaps.add(paperMap);

                String type = JSON.toJSONString(paperMaps);
                if (type.length() > 2) {
                    type = "{ \"articles\": [" + type.substring(1, type.length() - 1) + "    ], \"is_english\":" +
                            is_english+
                            "}";
                }

                String sendTypePost = HttpUtils.doPost(this.articlePath + "paper", type);
                if (sendTypePost.isEmpty()) {
                    continue;
                }
                Object pdf_path=((Map) maps.get(i)).get("pdf_path") ;
                if (pdf_path!= null&&!"".equals(pdf_path.toString())&&pdf_path.toString().endsWith(".pdf")) {
                    pdf_path = "D:/File/" + pdf_path.toString();
                }else {
                    pdf_path="123不存在";
                }



                Map map = (Map) maps.get(i);
                File outFile = new File(pdf_path.toString());
                boolean exists = outFile.exists();
                System.out.print("论文id----->" + ((Map) maps.get(i)).get("article_id") + "\n");
                System.out.print("文件是否存在----->" + exists + "\n");
                System.out.print("文件路径----->" + pdf_path + "\n");
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
                param.put("image_path", map.get("image_path") == null ? "" : map.get("image_path").toString());

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
