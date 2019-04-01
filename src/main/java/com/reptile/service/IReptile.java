package com.reptile.service;

import java.util.List;
import java.util.Map;

import com.reptile.entity.Paper;
import com.reptile.entity.PaperWithBLOBs;
import com.reptile.entity.ReptileEntity;

public interface IReptile {


    List getData(ReptileEntity record) throws Exception ;

    List getArticleData(ReptileEntity record) throws Exception ;

    int delByArticle(List<String> ids) throws Exception ;

    List<PaperWithBLOBs>  getPaperData(Paper paper) throws Exception ;

    int delByPaper(List<String> ids) throws Exception ;


}
