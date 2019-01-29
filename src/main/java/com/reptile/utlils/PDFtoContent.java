package com.reptile.utlils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;
import java.util.List;

public class PDFtoContent {


    public synchronized  static String  getContent(String fileName){

//        fileName="C:/Users/lk/Desktop/spring-boot-中文参考手册.pdf";
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream(new File(fileName)));
            PDDocument pdfDocument = PDDocument.load(inputStream);

/** 文档页面信息 **/
            PDDocumentCatalog cata = pdfDocument.getDocumentCatalog();
            //文档文字内容
            String content = "";
            List pages = cata.getAllPages();
            int count = 1;
            for (int i = 0; i < pages.size(); i++) {
                PDPage page = (PDPage) pages.get(i);
                if (null != page) {
                    // 本页面文字内容
                    StringWriter sw = new StringWriter();
                    PDFTextStripper pst = new PDFTextStripper();
                    pst.setStartPage(i + 1);
                    pst.setEndPage(i + 1);
                    pst.writeText(pdfDocument, sw);
                    content += sw.getBuffer().toString();

                }
            }
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
