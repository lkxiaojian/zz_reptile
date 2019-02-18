package com.reptile.service.iml;

import com.reptile.dao.AcademicPaperMapper;
import com.reptile.dao.KeywordMapper;
import com.reptile.service.ArticleService;
import com.reptile.service.ParperService;
import com.reptile.utlils.PDFtoContent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ParperServiceIml implements ParperService {

    @Resource
    private AcademicPaperMapper academicPaperMapper;
    @Override
    public Map<String, Object> getData(int rows, int page, String type) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("rows",rows);
        map.put("page",(page-1)*rows);
        map.put("type",type);

        List<Map<String, Object>> maps = academicPaperMapper.getData(map);
        int count=  academicPaperMapper.getCountByName(map);

        Map<String,Object> resultMap=new HashMap<>();
        //把pdf 转成字符串
//        for(int i=0;i<maps.size();i++){
//            Object pdf_path = maps.get(i).get("pdf_path");
//            if(pdf_path!=null){
//                maps.get(i).put("txt",PDFtoContent.getContent("D:/File/"+pdf_path.toString()));
//            }
//        }
        int num=count - rows*page;
        if(num<0){
            num=0;
        }
        resultMap.put("code",0);
        resultMap.put("message","请求成功");
        resultMap.put("count", num);
        resultMap.put("result",maps);
        return resultMap;
    }
}
