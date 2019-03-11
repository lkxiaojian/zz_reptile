package com.reptile.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.reptile.dao.ArticleMapper;
import com.reptile.dao.ArticleTypeMapper;
import com.reptile.dao.ReptileDao;
import com.reptile.entity.Article;
import com.reptile.entity.ArticleExample;
import com.reptile.entity.ArticleType;
import com.reptile.entity.ArticleTypeExample;
import com.reptile.entity.ArticleTypeExample.Criteria;
import com.reptile.entity.ArticleWithBLOBs;
import com.reptile.entity.IpPostEntity;
import com.reptile.service.Gather;
import com.reptile.service.IReptile;

@Component
@EnableScheduling
//@EnableAsync
public class SchedulerTask {
	private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);

		@Autowired
		private ArticleMapper articleMapper;

		@Autowired
		private IReptile reptileImpl;
		@Autowired
		private ReptileDao mapper;
		@Autowired
		private ArticleTypeMapper articleTypeMapper;

		@Autowired
		private Gather gather;

		private boolean bool = true;

		@Value("${ARTICLE_COOKIE}")
		private String ARTICLE_COOKIE ;

		@Value("${DATA_NUM}")
		private int DATA_NUM ;

		@Value("${KEYWORK_NUM}")
		private int KEYWORK_NUM ;

		@Value("${INTERVAL_DAY}")
		private Integer INTERVAL_DAY ;

	    @Scheduled(cron = "${TASK_TIME}")
	    public void job22() {
	    	job2(3);
	    }

	    @Scheduled(cron = "${TASK_TIME}")
	    public void job23() {
	    	job2(1);
	    }

//		@Scheduled(cron = "${TASK_TIME}")
//		public void job24() {
//			job2(5);
//		}

	    public void job2(int ii ){
	    	PageHelper.startPage(ii, DATA_NUM);
	    	ArticleExample example = new ArticleExample();
	    	com.reptile.entity.ArticleExample.Criteria c  =  example.createCriteria();
	    	c.andStateEqualTo(0);
	    	List<Article> list = articleMapper.selectByExample(example);
	    	Document document = null;
	    	ArticleWithBLOBs record = null;
	    	Element contentDiv = null;
	    	String contentTxt = null;
	    	String articleId = null;
	    	Random ran = new Random();
	    	String detailsPath = null;
	    	int i =0 ;
	    	String maxInfo ="";

	    	List<IpPostEntity> ipPost = null;
	    	if(list!=null&&list.size()>0) {
	    		IpPostEntity ipPostEntity = new IpPostEntity();
	    		ipPostEntity.setState(1);
	    		ipPost = mapper.selectIpPost(ipPostEntity);
	    	}
	    	for (Article article : list) {
	    		articleId = article.getArticleId();
	    		detailsPath = article.getDetailsPath();
				try {
					i=0;
					document = Gather.getHeader( ran, detailsPath,ipPost,i,ARTICLE_COOKIE);
					if(document!=null) {
						maxInfo = document.getElementsByTag("body").text();
						if(maxInfo==null||"Maximum number of open connections reached.".equals(maxInfo)||
								"".equals(maxInfo)||
								maxInfo.startsWith("Not Found")||
								maxInfo.indexOf("Internal Privoxy Error")!=-1||
								maxInfo.indexOf("Server dropped connection")!=-1||
								maxInfo.indexOf("Host Not Found or connection failed")!=-1
								) {

							if(article.getGetState()==2) {
								record.setState(3);
								record.setArticleId(articleId);
								articleMapper.updateByDetails(record);
							}else{
								if(article.getGetState()>3){
									articleTypeMapper.deleteById(articleId);
								}
								continue;
							}
							articleMapper.setGetStartAdd(articleId);
							continue;}
					}else {
						continue;
					}
					record = new ArticleWithBLOBs();
					 contentDiv = document.getElementById("img-content");
					try {
						String code1 = document.select("meta[http-equiv=Content-Type]").get(0).attr("content");
						System.out.println(articleId+"获取头信息"+code1);
					} catch (Exception e11) {
					}

					 if(contentDiv==null) {
						 if(article.getGetState()==2){
							 record.setState(3);
							 record.setArticleId(articleId);
							 articleMapper.updateByDetails(record);
						 }else{
							 if(article.getGetState()>3){
								 articleTypeMapper.deleteById(articleId);
							 }
						 }
						 articleMapper.setGetStartAdd(articleId);
						 continue;
					 }else {
						 contentTxt = contentDiv.text();
						 String code = guessEncoding(contentTxt.getBytes());
						if(code != null){
							contentTxt= new String( contentTxt.getBytes(code) ,"UTF-8");
						}
						 System.out.println(contentTxt);
						String div = contentDiv.toString();
						 div = div.replace("data-src=", "src=");
						 div = div.substring(0,div.indexOf("<script nonce"));
						 div =div+"</div>";
						 record.setDetailsDiv(div.getBytes());
						 record.setDetailsTxt(contentTxt.getBytes());
						 record.setCollectInitcount(contentTxt.length());
						 record.setState(1);
					 }
					 record.setArticleId(articleId);
					articleMapper.updateByDetails(record);
					articleId = null;

					Thread.sleep(ran.nextInt(2000));
				} catch (Exception e) {
					 try {
//						 Thread.sleep(ran.nextInt(18000));
						 log.error("插入文章链接错误！"+record+":"+e.toString());
						record.setState(3);
						 record.setDetailsDiv(null);
						 record.setDetailsTxt(null);
						articleMapper.updateByDetails(record);
					} catch (Exception e1) {
						log.error("修改文章状态为2错误！！"+record);
					}
				}
				log.info("插入文章链接："+detailsPath);
			}
	    }


//	    @Scheduled(cron = "${ArticleTask}")
	  	@Scheduled(initialDelay=100,fixedDelay=1000*60*5)
//		@Scheduled(cron = "0 0/1 * * * ? ")
//		@Scheduled(cron = "0 0 0/1 * * ? ")
//		@Async
	public void job1(){
		try {
			ArticleTypeExample example = new ArticleTypeExample();
			Criteria cl  = example.createCriteria();
			cl.andParentidNotEqualTo(0);

			List<ArticleType> listArticleType= new ArrayList<ArticleType>();
			if(bool){
				PageHelper.startPage(1, KEYWORK_NUM);
				listArticleType = articleTypeMapper.selectKeyWork(INTERVAL_DAY,0,0);

			}else{
				Calendar cld = Calendar.getInstance();//可以对每个时间域单独修改
				int hour = cld.get(Calendar.HOUR_OF_DAY);
				listArticleType = articleTypeMapper.selectKeyWork(INTERVAL_DAY,24,hour);
			}
			IpPostEntity ipPostEntity = new IpPostEntity();
			ipPostEntity.setState(1);
			List<IpPostEntity> ipPost = mapper.selectIpPost(ipPostEntity);

//		    	Calendar cld = Calendar.getInstance();//可以对每个时间域单独修改
//		    	int hour = cld.get(Calendar.HOUR_OF_DAY);
//		    	if(hour<listArticleType.size()) {
//		    		gather.setData(1,listArticleType.get(hour),ipPost);
//		    	}
			for (ArticleType articleType : listArticleType) {
				gather.setData(1,articleType,ipPost);
				log.info(articleType.getArticleTypeKeyword()+"插入结束");
				articleTypeMapper.updateLastTime(articleType);
			}
//		    	articleTypeMapper.deleteByPrimaryKey(2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	@Scheduled(initialDelay=100,fixedDelay=1000*60*5)
	public void job12(){
		try {
			ArticleTypeExample example = new ArticleTypeExample();
			Criteria cl  = example.createCriteria();
			cl.andParentidNotEqualTo(0);

			List<ArticleType> listArticleType= new ArrayList<ArticleType>();
			listArticleType = articleTypeMapper.selectAllKeyWork(0);

			IpPostEntity ipPostEntity = new IpPostEntity();
			ipPostEntity.setState(1);
			List<IpPostEntity> ipPost = mapper.selectIpPost(ipPostEntity);

			for (ArticleType articleType : listArticleType) {
				gather.setData(1,articleType,ipPost);
				log.info(articleType.getArticleTypeKeyword()+"插入结束");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String guessEncoding(byte[] bytes) {
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		return encoding;
	}

}
