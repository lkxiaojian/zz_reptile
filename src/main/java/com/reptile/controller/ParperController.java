package com.reptile.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reptile.entity.ArticleWithBLOBs;
import com.reptile.entity.ReptileEntity;
import com.reptile.service.IReptile;
import com.reptile.service.iml.ParperServiceIml;
import com.reptile.utlils.PDFtoContent;
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
@RequestMapping("/parper")
public class ParperController {

	@Autowired
	private ParperServiceIml parperServiceIml;
	
	@RequestMapping("/getData")
    public Map getData(int rows, int page, String type) throws Exception{


		Map map = new HashMap();
		try {
			map=parperServiceIml.getData(rows,page,type);
		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}
    	
		return map;
	}

	/**
	 * 获取微信文章
	 * @param rows
	 * @param page
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWxData")
	public Map getWxData(int rows, int page, String type) throws Exception{


		Map map = new HashMap();
		try {
			map=parperServiceIml.getWxData(rows,page,type);
		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}


	/**
	 * 获取微信文章
	 * @param rows
	 * @param page
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWxDataById")
	public Map getWxDataById(int rows, int page, String typeId) throws Exception{


		Map map = new HashMap();
		try {
			map=parperServiceIml.getWxDataById(rows,page,typeId);
		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}


	/**
	 * 获取微信文章
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWxDataMessageById")
	public Map getWxDataMessageById(String id ) throws Exception{


		Map map = new HashMap();
		try {
			map=parperServiceIml.getWxDataMessageById(id);
		} catch (Exception e) {
			map.put("msg", "系统程序出错，请联系管理员！");
			map.put("code",500);
		}

		return map;
	}
}
