package com.reptile.task;

import com.github.pagehelper.PageHelper;
import com.reptile.dao.ArticleMapper;
import com.reptile.dao.ArticleTypeMapper;
import com.reptile.dao.ReptileDao;
import com.reptile.entity.*;
import com.reptile.service.Gather;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Description: zz_reptile
 * Created by s on 2019/2/27 21:45
 */
//@Component
//@EnableScheduling
public class ArticleAgainTask {

    private static final Logger log = LoggerFactory.getLogger(ArticleAgainTask.class);


    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ReptileDao mapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Value("${WEB_COOKIE}")
    private String WEB_COOKIE ;
    @Value("${WEB_CONDITION}")
    private String WEB_CONDITION ;

    private String webUrl =  "http://weixin.sogou.com/weixin?type=2&s_from=input&query=queryArticleTitle&ie=utf8&_sug_=n&_sug_type_=";


//    @Scheduled(cron = "${ArticleTask}")
    @Scheduled(cron = "0 0/10 * * * ?")

    public void job1(){
        try {
            PageHelper.startPage(1, 15);
            ArticleExample example = new ArticleExample();
            com.reptile.entity.ArticleExample.Criteria c  =  example.createCriteria();
            c.andStateEqualTo(3);
            List<Article> list = articleMapper.selectByExample(example);

            if(list.size()>0){
                IpPostEntity ipPostEntity = new IpPostEntity();
                ipPostEntity.setState(1);
                List<IpPostEntity> ipPost = mapper.selectIpPost(ipPostEntity);
                for (Article article:list) {
                    articleTypeMapper.deleteById(article.getArticleId());
                    setData(article,ipPost);
                    Thread.sleep(15000);
                    log.info("重新访问标题：{},{}",article.getArticleId(),article.getArticleTitle());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setData(Article article,List<IpPostEntity> ipPost) throws Exception {

        Random ran =  new Random() ;
        ReptileEntity reptileEntity=null;
        Elements imgtxtBox =null;
        String detailsPath =null;
        String articleId = null;
        String articleTitle =null;
        String contentExcerpt = null;
        String source = null;
        Long createTime = 0L;
        Document document = null;
        int i = 0;
        String maxInfo ="";

        String url = webUrl.replace("queryArticleTitle",article.getArticleTitle());

            document = Gather.getHeader(ran,url,ipPost,i,WEB_COOKIE);
            if(document!=null) {
                maxInfo = document.getElementsByTag("body").text();
                if(maxInfo==null||
                        "Maximum number of open connections reached.".equals(maxInfo)||
                        "".equals(maxInfo)||
                        maxInfo.startsWith("Not Found")||
                        maxInfo.indexOf("Internal Privoxy Error")!=-1||
                        maxInfo.indexOf("Server dropped connection")!=-1||
                        maxInfo.indexOf("Host Not Found or connection failed")!=-1||
                        maxInfo.length()<350
                ) {
                    return;
                }
            }else {
                return;
            }
            Element elements = document.getElementsByClass("news-list").last();
            if(elements !=null ) {
                Elements lis = elements.getElementsByTag("li");
                if(lis != null) {

                    for (Element e : lis) {

                        reptileEntity = new ReptileEntity();
                        reptileEntity.setArticleTypeId(article.getArticleTypeId());

                        imgtxtBox = e.getElementsByTag("div");

                        articleId = e.attr("d");
                        articleId = articleId.substring(articleId.lastIndexOf("-")+1);
                        reptileEntity.setArticleId(articleId);

                        detailsPath = imgtxtBox.select("a").first().attr("href");
                        reptileEntity.setDetailsPath(detailsPath);

                        reptileEntity.setContentCrawl(imgtxtBox.toString().getBytes());

                        articleTitle = imgtxtBox.select("h3").last().text();
                        reptileEntity.setArticleTitle(articleTitle);

                        reptileEntity.setArticleKeyword(article.getArticleKeyword());

                        contentExcerpt = imgtxtBox.select("p").last().text();
                        reptileEntity.setContentExcerpt(contentExcerpt);

                        Element txtBox2 = imgtxtBox.get(2);
                        source = txtBox2.getElementsByTag("a").first().text();
                        reptileEntity.setSource(source);
                        createTime =  Long.valueOf(txtBox2.attr("t"));
                        reptileEntity.setCreateTime( createTime);

                        reptileEntity.setContentType(1);

                        mapper.insert(reptileEntity);
                        break;
                    }
                }

            }
    }

}
