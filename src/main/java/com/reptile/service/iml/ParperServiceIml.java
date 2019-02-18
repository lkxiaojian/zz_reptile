package com.reptile.service.iml;

import com.reptile.dao.AcademicPaperMapper;
import com.reptile.dao.Article1Mapper;
import com.reptile.entity.Article1;
import com.reptile.service.ParperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ParperServiceIml implements ParperService {

    @Resource
    private AcademicPaperMapper academicPaperMapper;

    @Resource
    private Article1Mapper article1Mapper;
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

    /**
     * 获取微信文章
     * @param rows
     * @param page
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public Map getWxData(int rows, int page, String type) throws Exception {
        Map<String,Object> paremMap=new HashMap<>();
        paremMap.put("rows",rows);
        paremMap.put("page",(page - 1) * rows);
        paremMap.put("type",type);

        int wxDataCount = article1Mapper.getWxDataCount(paremMap);
        List<Map<String, Object>> maps=  article1Mapper.getWxData(paremMap);
        for (int i = 0; i < maps.size(); i++) {
            Object details_div = maps.get(i).get("details_txt");
            Object article_title = maps.get(i).get("article_title");

            byte[] details_divbytes = (byte[]) details_div;
            if (details_div != null && article_title != null) {
                try {
                    String s = new String(details_divbytes, "UTF-8").replaceAll(" ","").replaceAll("\\s","").replaceAll(",","，").replaceAll("!","！").replaceAll("\\.","。").replaceAll("\\[","】")
                            .replaceAll("]","】").replaceAll("\\(","（").replaceAll("\\)","）").replaceAll("\\|","|")
                            .replaceAll("-","—");
                    String s1 = article_title.toString().replaceAll(",", "，").replaceAll("!","！").replaceAll("\\.","。").replaceAll("\\[","】")
                            .replaceAll("]","】").replaceAll("\\(","（").replaceAll("\\)","）").replaceAll("\\|","|")
                            .replaceAll("-","—").replaceAll(" ","").replaceAll("\\s","");
                    s =s.replaceAll(s1, "");
                    maps.get(i).put("details_txt",s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", wxDataCount- page * rows);
        resultMap.put("txt", maps);
        map.put("code", 0);
        map.put("result", resultMap);

        return map;
    }

}
