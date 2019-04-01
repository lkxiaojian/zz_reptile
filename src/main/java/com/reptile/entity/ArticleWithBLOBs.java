package com.reptile.entity;

public class ArticleWithBLOBs extends Article {

    private String txt ;
    private int num;

    private byte[] contentCrawl;

    private byte[] detailsTxt;

    private byte[] detailsDiv;

    public byte[] getContentCrawl() {
        return contentCrawl;
    }

    public void setContentCrawl(byte[] contentCrawl) {
        this.contentCrawl = contentCrawl;
    }

    public byte[] getDetailsTxt() {
        return detailsTxt;
    }

    public void setDetailsTxt(byte[] detailsTxt) {
        this.detailsTxt = detailsTxt;
    }

    public byte[] getDetailsDiv() {
        return detailsDiv;
    }

    public void setDetailsDiv(byte[] detailsDiv) {
        this.detailsDiv = detailsDiv;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
