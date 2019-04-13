package com.reptile.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reptile.entity.ArticleWithBLOBs;
import com.reptile.entity.Paper;
import com.reptile.entity.PaperWithBLOBs;
import com.reptile.entity.ReptileEntity;
import com.reptile.service.IReptile;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reptile")
@CrossOrigin
public class ReptileController {

	@Autowired
	private IReptile reptileImpl;

	@Value("${PARPER_PATH}")
	public String parperPath;

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
				String code;
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

	@GetMapping("/delByPaper")
	public Map delByPaper(HttpServletRequest request,HttpServletResponse response,  List<String> ids) throws Exception{
		Map map = new HashMap();
		try {
			int i = reptileImpl.delByPaper(ids);
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


	@GetMapping("/getExcelArticleData")
	public void getExcelArticleData(HttpServletRequest request,HttpServletResponse response,  ReptileEntity reptileEntity
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "size", defaultValue = "1000") Integer size) throws Exception{
		try {
			PageHelper.startPage(page, size);
			List<ArticleWithBLOBs> list = reptileImpl.getArticleData(reptileEntity);

			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			//2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
			response.setHeader("Content-Disposition", "attachment;fileName=Article.xlsx");
			OutputStream out =response.getOutputStream();
			ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
			Sheet sheet = new Sheet(1, 0);
			List<List<String>> data = new ArrayList<>();
			String code;
			for (ArticleWithBLOBs articleWithBLOBs : list) {
				code = guessEncoding(articleWithBLOBs.getDetailsTxt());
				List<String> item = new ArrayList<>();
				item.add(articleWithBLOBs.getArticleTitle());
				item.add(articleWithBLOBs.getCreateTime());
				item.add(articleWithBLOBs.getUpdateTime());
				item.add(articleWithBLOBs.getSource());
				item.add(articleWithBLOBs.getArticleKeyword());
//				item.add(articleWithBLOBs.getAuthor());
				item.add(articleWithBLOBs.getContentExcerpt());
				if(null!=code){
					String txt = new String(articleWithBLOBs.getDetailsTxt(),code);
					item.add(txt);
					item.add(txt.length()+"");
				}else{
					String txt = new String(articleWithBLOBs.getDetailsTxt());
					item.add(txt);
					item.add(txt.length()+"");
				}
				data.add(item);
			}

			List<List<String>> head = new ArrayList<List<String>>();
			List<String> headCoulumn1 = new ArrayList<String>();
			List<String> headCoulumn2 = new ArrayList<String>();
			List<String> headCoulumn3 = new ArrayList<String>();
			List<String> headCoulumn33 = new ArrayList<String>();
			List<String> headCoulumn4 = new ArrayList<String>();
//			List<String> headCoulumn5 = new ArrayList<String>();
			List<String> headCoulumn6 = new ArrayList<String>();
			List<String> headCoulumn7 = new ArrayList<String>();
			List<String> headCoulumn8 = new ArrayList<String>();

			headCoulumn1.add("文章标题");
			headCoulumn2.add("创建时间");
			headCoulumn3.add("入库时间");
			headCoulumn33.add("来源");
			headCoulumn4.add("关键词");
//			headCoulumn5.add("作者");
			headCoulumn6.add("文章摘要");
			headCoulumn7.add("文章内容");
			headCoulumn8.add("字数");
			head.add(headCoulumn1);
			head.add(headCoulumn2);
			head.add(headCoulumn3);
			head.add(headCoulumn33);
			head.add(headCoulumn4);
//			head.add(headCoulumn5);
			head.add(headCoulumn6);
			head.add(headCoulumn7);
			head.add(headCoulumn8);

			Table table = new Table(1);
			table.setHead(head);

			writer.write0(data,sheet,table);
			writer.finish();

		} catch (Exception e) {
		}

	}

	@GetMapping("/getExcelPaperData")
	public void getExcelPaperData(HttpServletRequest request,HttpServletResponse response,  Paper paper
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "size", defaultValue = "1000") Integer size) throws Exception{
		Map map = new HashMap();
		try {
			PageHelper.startPage(page, size);
			List<PaperWithBLOBs>  list = reptileImpl.getPaperData(paper);
			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			//2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
			response.setHeader("Content-Disposition", "attachment;fileName=Paper.xlsx");
			OutputStream out =response.getOutputStream();
			ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
			Sheet sheet = new Sheet(1, 0);
			List<List<String>> data = new ArrayList<>();

			for (PaperWithBLOBs paperWithBLOBs : list) {
				List<String> item = new ArrayList<>();
				item.add(paperWithBLOBs.getArticleTitle());
				item.add(paperWithBLOBs.getCreateTime());
				item.add(paperWithBLOBs.getUpdateTime());
				item.add(paperWithBLOBs.getArticleKeyword());
				item.add(paperWithBLOBs.getArticleKeywordE());
				item.add(paperWithBLOBs.getAuthor());
				item.add(paperWithBLOBs.getAuthorE());
				item.add(paperWithBLOBs.getContentExcerpt());
				item.add(paperWithBLOBs.getContentExcerptE());
				item.add(paperWithBLOBs.getPdfPath());
				item.add(paperWithBLOBs.getPublicationDate());
				item.add(paperWithBLOBs.getReference());
				item.add(paperWithBLOBs.getPostingName());
//				item.add(paperWithBLOBs.getSource());

				data.add(item);
			}

			List<List<String>> head = new ArrayList<List<String>>();
			List<String> headCoulumn1 = new ArrayList<String>();
			List<String> headCoulumn2 = new ArrayList<String>();
			List<String> headCoulumn3 = new ArrayList<String>();
			List<String> headCoulumn4 = new ArrayList<String>();
			List<String> headCoulumn44 = new ArrayList<String>();
			List<String> headCoulumn5 = new ArrayList<String>();
			List<String> headCoulumn55 = new ArrayList<String>();
			List<String> headCoulumn6 = new ArrayList<String>();
			List<String> headCoulumn66 = new ArrayList<String>();
			List<String> headCoulumn7 = new ArrayList<String>();
			List<String> headCoulumn8 = new ArrayList<String>();
			List<String> headCoulumn9 = new ArrayList<String>();
			List<String> headCoulumn10 = new ArrayList<String>();
//			List<String> headCoulumn11 = new ArrayList<String>();

			headCoulumn1.add("论文标题");
			headCoulumn2.add("创建时间");
			headCoulumn3.add("入库时间");
			headCoulumn4.add("关键词");
			headCoulumn44.add("英文关键词");
			headCoulumn5.add("作者");
			headCoulumn55.add("英文作者");
			headCoulumn6.add("摘要");
			headCoulumn66.add("英文摘要");
			headCoulumn7.add("路径");
			headCoulumn8.add("刊出卷期");
			headCoulumn9.add("参考文献");
			headCoulumn10.add("刊期名称");
//			headCoulumn11.add("来源");
			head.add(headCoulumn1);
			head.add(headCoulumn2);
			head.add(headCoulumn3);
			head.add(headCoulumn4);
			head.add(headCoulumn44);
			head.add(headCoulumn5);
			head.add(headCoulumn55);
			head.add(headCoulumn6);
			head.add(headCoulumn66);
			head.add(headCoulumn7);
			head.add(headCoulumn8);
			head.add(headCoulumn9);
			head.add(headCoulumn10);
//			head.add(headCoulumn11);

			Table table = new Table(1);
			table.setHead(head);
			writer.write0(data,sheet,table);
			writer.finish();
		} catch (Exception e) {
		}

	}

	public String BufferedReaderDemo(String path) throws Exception {
		FileReader fileReader= null ;
		BufferedReader br = null;
		try {
			File file = new File(parperPath+path);
			if (!file.exists() || file.isDirectory())
				throw new FileNotFoundException();
			fileReader=new FileReader(file);
			br = new BufferedReader(fileReader);
			String temp = null;
			StringBuffer sb = new StringBuffer();
			temp = br.readLine();
			while (temp != null) {
				sb.append(temp + " ");
				temp = br.readLine();
			}
			return sb.toString();
		}catch (Exception e) {
			return "";
		}finally {
			if(br!=null){br.close();}
			if(fileReader!=null){fileReader.close();}
		}
		}
}
