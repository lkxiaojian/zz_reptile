package com.reptile.task;

import com.github.pagehelper.PageHelper;
import com.reptile.dao.ArticleMapper;
import com.reptile.dao.ArticleTypeMapper;
import com.reptile.dao.ReptileDao;
import com.reptile.entity.*;
import com.reptile.service.Gather;
import com.reptile.service.IReptile;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Component
//@EnableScheduling
public class ArticleTask {

	private static final Logger log = LoggerFactory.getLogger(ArticleTask.class);

	@Autowired
	private ArticleMapper articleMapper;

	@Value("${ARTICLE_COOKIE}")
	private String ARTICLE_COOKIE ;
	@Value("${DATA_NUM}")
	private int DATA_NUM ;

	@Autowired
	private IReptile reptileImpl;

	private volatile List list;

	@Autowired
	private ReptileDao mapper;
	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	@Autowired
	private Gather gather;


	@Scheduled(cron = "${TASK_TIME}")
	public void job22() {
		job2(1);
	}

	@Scheduled(cron = "${TASK_TIME}")
	public void job23() {
		job2(9);
	}

	@Scheduled(cron = "${TASK_TIME}")
	public void job24() {
		job2(5);
	}

//	@Scheduled(cron = "0 0/5 * * * ?")
//	public void job25() {
//		job2(7);
//	}
//
//	@Scheduled(cron = "0 0/5 * * * ?")
//	public void job26() {
//		job2(9);
//	}

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
						}
						articleMapper.setGetStartAdd(articleId);
						continue;}
				}else {
					continue;
				}
				record = new ArticleWithBLOBs();
				contentDiv = document.getElementById("img-content");
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
//					contentTxt=new String(contentTxt.getBytes(),"UTF-8");
					String code = guessEncoding(contentTxt.getBytes());
					if(code != null){
						contentTxt= new String( contentTxt.getBytes(code) ,"UTF-8");
					}

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


//	@Scheduled(initialDelay = 3000)
//	 @Scheduled(cron = "0 0 4 * * ?")
	@Scheduled(cron = "${setIpPost}")
    public void job3(){
		 IpPostEntity ipPostEntity = new IpPostEntity();
		List<IpPostEntity> l = mapper.selectIpPost(ipPostEntity);
		for (IpPostEntity i : l) {
			try {
				connect(i.getIp(),i.getPost());
				i.setState(1);
				mapper.insertsIpPost(i);
			} catch (Exception e) {
				i.setState(0);
				mapper.insertsIpPost(i);
			}
		}

		ipPostEntity.setState(1);
		l = mapper.selectIpPost(ipPostEntity);

		log.info("刷新IP成功，当前IP数量："+l.size());

   }


	public void connect(String server, int servPort) throws Exception {
        Socket socket = new Socket();
        socket.setReceiveBufferSize(servPort);
        socket.setSoTimeout(3000);
        SocketAddress address = new InetSocketAddress(server, servPort);
        socket.connect(address,3000);//1.判断ip、端口是否可连接
    }

	public static String guessEncoding(byte[] bytes) {
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		return encoding;
	}


		@Scheduled(initialDelay=100,fixedDelay=1000*60*5)
	public void job12(){
		try {
			ArticleTypeExample example = new ArticleTypeExample();
			ArticleTypeExample.Criteria cl  = example.createCriteria();
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

}
