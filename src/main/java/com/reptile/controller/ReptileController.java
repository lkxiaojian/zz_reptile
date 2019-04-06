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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reptile")
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
				item.add(articleWithBLOBs.getAuthor());
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
			headCoulumn1.add("第一列");
			headCoulumn2.add("第二列");
			headCoulumn3.add("第三列");
			head.add(headCoulumn1);
			head.add(headCoulumn2);
			head.add(headCoulumn3);
			Table table = new Table(1);
			table.setHead(head);

			writer.write0(data,sheet);
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
				item.add(paperWithBLOBs.getSource());
				item.add(paperWithBLOBs.getAuthor());
				item.add(paperWithBLOBs.getAuthorE());
				item.add(paperWithBLOBs.getArticleKeyword());
				item.add(paperWithBLOBs.getArticleKeywordE());
				item.add(paperWithBLOBs.getPdfPath());
				item.add(paperWithBLOBs.getCreateTime());
				item.add(paperWithBLOBs.getContentExcerpt());
				item.add(paperWithBLOBs.getContentExcerptE());
				item.add(paperWithBLOBs.getReference());
				item.add(paperWithBLOBs.getPublicationDate());
				item.add(paperWithBLOBs.getPostingName());
				item.add(paperWithBLOBs.getUpdateTime());

				data.add(item);
			}
			writer.write0(data,sheet);
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
