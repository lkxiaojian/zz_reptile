package com.reptile.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reptile.entity.ArticleWithBLOBs;
import com.reptile.entity.Paper;
import com.reptile.entity.PaperWithBLOBs;
import com.reptile.entity.ReptileEntity;
import com.reptile.service.IReptile;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reptile")
public class ReptileController {

	@Autowired
	private IReptile reptileImpl;

	@GetMapping("/getData")
    public Map getData(HttpServletRequest request,HttpServletResponse response,  ReptileEntity reptileEntity
    		,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) throws Exception{


		Map map = new HashMap();
		try {
//			if(reptileEntity==null&&reptileEntity.getArticleTypeId()==null) {
//				map.put("code",302);
//				map.put("msg", "请求文章类型id不可为空！");
//				return map;
//			}

			if(rows>1000) {
				map.put("code",302);
				map.put("msg", "数据查询条数过多，请重新请求！");
			}else {
				PageHelper.startPage(page, rows);
				List<ArticleWithBLOBs> list = reptileImpl.getData(reptileEntity);
				List dataList = new ArrayList();
				String code="";
				Map map1 = new HashMap();
				for (ArticleWithBLOBs articleWithBLOBs : list) {
					map1 = new HashMap();

					code = guessEncoding(articleWithBLOBs.getDetailsTxt());
					if(null!=code){
						map1.put("txt", new String(articleWithBLOBs.getDetailsTxt(),code));
					}else{
						map1.put("txt", new String(articleWithBLOBs.getDetailsTxt()));
					}

					map1.put("articleId", articleWithBLOBs.getArticleId());
					dataList.add(map1);
				}
				PageInfo pageInfo = new PageInfo(list);
				map.put("count", pageInfo.getTotal());

				map.put("data", dataList);
				map.put("msg", "数据返回成功！");
				map.put("code",200);
			}

		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}

	public static String guessEncoding(byte[] bytes) {
		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		return encoding;
	}



	@GetMapping("/getArticleData")
	public Map getArticleData(HttpServletRequest request,HttpServletResponse response,  ReptileEntity reptileEntity
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) throws Exception{
		Map map = new HashMap();
		try {
			PageHelper.startPage(page, rows);
			List<ArticleWithBLOBs> list = reptileImpl.getArticleData(reptileEntity);

			String code="";
			for (ArticleWithBLOBs articleWithBLOBs : list) {
				code = guessEncoding(articleWithBLOBs.getDetailsTxt());
				if(null!=code){
					String txt = new String(articleWithBLOBs.getDetailsTxt(),code);
					articleWithBLOBs.setTxt(txt);
					articleWithBLOBs.setNum(txt.length());
				}else{
					String txt = new String(articleWithBLOBs.getDetailsTxt());
					articleWithBLOBs.setTxt(txt);
					articleWithBLOBs.setNum(txt.length());
				}
			}
			PageInfo pageInfo = new PageInfo(list);
			map.put("count", pageInfo.getTotal());

			map.put("data", list);
			map.put("msg", "数据返回成功！");
			map.put("code",200);

		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}


	@GetMapping("/delByArticle")
	public Map delByArticle(HttpServletRequest request,HttpServletResponse response,  List<String> ids) throws Exception{
		Map map = new HashMap();
		try {
				int i = reptileImpl.delByArticle(ids);
				map.put("num", i);
				map.put("msg", "删除数据成功！");
				map.put("code",200);
		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}
		return map;
	}


	@GetMapping("/getPaperData")
	public Map getPaperData(HttpServletRequest request,HttpServletResponse response,  Paper paper
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) throws Exception{
		Map map = new HashMap();
		try {
			PageHelper.startPage(page, rows);
			List<PaperWithBLOBs>  list = reptileImpl.getPaperData(paper);

			List dataList = new ArrayList();
			String code="";
//			for (PaperWithBLOBs articleWithBLOBs : list) {
//				code = guessEncoding(articleWithBLOBs.get);
//				if(null!=code){
//					String txt = new String(articleWithBLOBs.getDetailsTxt(),code);
//					articleWithBLOBs.setTxt(txt);
//					articleWithBLOBs.setNum(txt.length());
//				}else{
//					String txt = new String(articleWithBLOBs.getDetailsTxt());
//					articleWithBLOBs.setTxt(txt);
//					articleWithBLOBs.setNum(txt.length());
//				}
//			}
			PageInfo pageInfo = new PageInfo(list);
			map.put("count", pageInfo.getTotal());

			map.put("data", list);
			map.put("msg", "数据返回成功！");
			map.put("code",200);

		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}

}
