package com.reptile.entity;

import java.io.Serializable;
import java.util.Date;

public class Article1 implements Serializable {
    private Date recordTime;

    private String articleId;

    private String articleTypeId;

    private String articleTitle;

    private String articleKeyword;

    private String author;

    private Date updateTime;

    private Date createTime;

    private String source;

    private Integer shareCount;

    private Integer collectCount;

    private Integer collectInitcount;

    private Integer shareInitcount;

    private Integer contentType;

    private Integer contentReadcount;

    private String contentExcerpt;

    private String imagePath;

    private Integer state;

    private String detailsPath;

    private Integer getState;

    private Integer detailsSize;

    private byte[] contentCrawl;

    private String contentManual;

    private byte[] detailsTxt;

    private byte[] detailsDiv;

    private static final long serialVersionUID = 1L;

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId == null ? null : articleTypeId.trim();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleKeyword() {
        return articleKeyword;
    }

    public void setArticleKeyword(String articleKeyword) {
        this.articleKeyword = articleKeyword == null ? null : articleKeyword.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getCollectInitcount() {
        return collectInitcount;
    }

    public void setCollectInitcount(Integer collectInitcount) {
        this.collectInitcount = collectInitcount;
    }

    public Integer getShareInitcount() {
        return shareInitcount;
    }

    public void setShareInitcount(Integer shareInitcount) {
        this.shareInitcount = shareInitcount;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentReadcount() {
        return contentReadcount;
    }

    public void setContentReadcount(Integer contentReadcount) {
        this.contentReadcount = contentReadcount;
    }

    public String getContentExcerpt() {
        return contentExcerpt;
    }

    public void setContentExcerpt(String contentExcerpt) {
        this.contentExcerpt = contentExcerpt == null ? null : contentExcerpt.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDetailsPath() {
        return detailsPath;
    }

    public void setDetailsPath(String detailsPath) {
        this.detailsPath = detailsPath == null ? null : detailsPath.trim();
    }

    public Integer getGetState() {
        return getState;
    }

    public void setGetState(Integer getState) {
        this.getState = getState;
    }

    public Integer getDetailsSize() {
        return detailsSize;
    }

    public void setDetailsSize(Integer detailsSize) {
        this.detailsSize = detailsSize;
    }

    public byte[] getContentCrawl() {
        return contentCrawl;
    }

    public void setContentCrawl(byte[] contentCrawl) {
        this.contentCrawl = contentCrawl;
    }

    public String getContentManual() {
        return contentManual;
    }

    public void setContentManual(String contentManual) {
        this.contentManual = contentManual == null ? null : contentManual.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordTime=").append(recordTime);
        sb.append(", articleId=").append(articleId);
        sb.append(", articleTypeId=").append(articleTypeId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleKeyword=").append(articleKeyword);
        sb.append(", author=").append(author);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", source=").append(source);
        sb.append(", shareCount=").append(shareCount);
        sb.append(", collectCount=").append(collectCount);
        sb.append(", collectInitcount=").append(collectInitcount);
        sb.append(", shareInitcount=").append(shareInitcount);
        sb.append(", contentType=").append(contentType);
        sb.append(", contentReadcount=").append(contentReadcount);
        sb.append(", contentExcerpt=").append(contentExcerpt);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", state=").append(state);
        sb.append(", detailsPath=").append(detailsPath);
        sb.append(", getState=").append(getState);
        sb.append(", detailsSize=").append(detailsSize);
        sb.append(", contentCrawl=").append(contentCrawl);
        sb.append(", contentManual=").append(contentManual);
        sb.append(", detailsTxt=").append(detailsTxt);
        sb.append(", detailsDiv=").append(detailsDiv);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}