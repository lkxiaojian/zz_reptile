package com.reptile.entity;

import java.util.Date;

public class Paper {
    private String stateTIme;
    private String endTime;
    private Integer articleId;

    private String articleTitle;

    private String articleKeyword;

    private String author;

    private Date updateTime;

    private String createTime;

    private String source;

    private Integer contentType;

    private String contentExcerpt;

    private String postingName;

    private String articleTitleE;

    private Integer wordCount;

    private String contentExcerptE;

    private String pdfPath;

    private String articleKeywordE;

    private String authorE;

    private Integer siteNumber;

    private String seachKeyword;

    private String publicationDate;

    public Integer getArticleId() {
        return articleId;
    }

    public String getStateTIme() {
        return stateTIme;
    }

    public void setStateTIme(String stateTIme) {
        this.stateTIme = stateTIme;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContentExcerpt() {
        return contentExcerpt;
    }

    public void setContentExcerpt(String contentExcerpt) {
        this.contentExcerpt = contentExcerpt == null ? null : contentExcerpt.trim();
    }

    public String getPostingName() {
        return postingName;
    }

    public void setPostingName(String postingName) {
        this.postingName = postingName == null ? null : postingName.trim();
    }

    public String getArticleTitleE() {
        return articleTitleE;
    }

    public void setArticleTitleE(String articleTitleE) {
        this.articleTitleE = articleTitleE == null ? null : articleTitleE.trim();
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public String getContentExcerptE() {
        return contentExcerptE;
    }

    public void setContentExcerptE(String contentExcerptE) {
        this.contentExcerptE = contentExcerptE == null ? null : contentExcerptE.trim();
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath == null ? null : pdfPath.trim();
    }

    public String getArticleKeywordE() {
        return articleKeywordE;
    }

    public void setArticleKeywordE(String articleKeywordE) {
        this.articleKeywordE = articleKeywordE == null ? null : articleKeywordE.trim();
    }

    public String getAuthorE() {
        return authorE;
    }

    public void setAuthorE(String authorE) {
        this.authorE = authorE == null ? null : authorE.trim();
    }

    public Integer getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(Integer siteNumber) {
        this.siteNumber = siteNumber;
    }

    public String getSeachKeyword() {
        return seachKeyword;
    }

    public void setSeachKeyword(String seachKeyword) {
        this.seachKeyword = seachKeyword == null ? null : seachKeyword.trim();
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate == null ? null : publicationDate.trim();
    }
}
