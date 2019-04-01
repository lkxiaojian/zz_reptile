package com.reptile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.reptile.dao.PaperMapper;
import com.reptile.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reptile.dao.ArticleMapper;
import com.reptile.dao.ArticleTypeMapper;
import com.reptile.dao.ReptileDao;
import com.reptile.entity.ArticleTypeExample.Criteria;

@Service
public class ReptileImpl implements IReptile{

	private static final Logger log = LoggerFactory.getLogger(ReptileImpl.class);
	@Autowired
	private ReptileDao mapper;

	@Autowired
	private Gather gather;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private ArticleTypeMapper articleTypeMapper;

	@Autowired
	private PaperMapper paperMapper;


	@Override
	public List getData(ReptileEntity record) throws Exception {
		ArticleExample articleExample =new ArticleExample();
		com.reptile.entity.ArticleExample.Criteria c = articleExample.createCriteria();
//		c.andArticleTypeIdEqualTo(record.getArticleTypeId());
		c.andStateEqualTo(1);
		List<ArticleWithBLOBs> list = articleMapper.selectArticleData(articleExample);
//		List idList = new ArrayList();
//		List dataList = new ArrayList();
////
//		Map map = new HashMap();
//		for (ArticleWithBLOBs articleWithBLOBs : list) {
////			idList.add(articleWithBLOBs.getArticleId());
//			map = new HashMap();
//			map.put("txt", new String(articleWithBLOBs.getDetailsTxt(),"UTF-8"));
//			map.put("articleId", articleWithBLOBs.getArticleId());
////			map.put("articleTypeId", articleWithBLOBs.getArticleTypeId());
////			map.put("articleKeyword", articleWithBLOBs.getArticleKeyword());
//			dataList.add(map);
//		}
//		if(articleMapper.updateDataState(idList)>0) {
//			log.info("抽取数据："+idList.size()+"条");
//			return dataList;
//		}

		return list;
	}

	@Override
	public List getArticleData(ReptileEntity record) throws Exception {
		if(record.getArticleTitle()!=null){
			record.setArticleTitle(record.getArticleKeyword()+"%");
		}
		List<ArticleWithBLOBs> list = articleMapper.selArticleData(record);
		return list;
	}

	@Override
	public int delByArticle(List<String> ids) throws Exception {
		int i= 0 ;
		for (String id :ids) {
			i+=articleTypeMapper.deleteById(id);
		}

		return i;
	}

	@Override
	public List<PaperWithBLOBs>  getPaperData(Paper paper) throws Exception {
		PaperExample paperExample=  new PaperExample();
		com.reptile.entity.PaperExample.Criteria criteria = paperExample.createCriteria();
		List<PaperWithBLOBs>  l = paperMapper.selectByExampleWithBLOBs(paperExample);
		return l;
	}

	@Override
	public int delByPaper(List<String> ids) throws Exception {

		int i= 0 ;
		for (String id :ids) {
			i+=paperMapper.deleteByPaperId(id);
		}

		return i;


	}

}
