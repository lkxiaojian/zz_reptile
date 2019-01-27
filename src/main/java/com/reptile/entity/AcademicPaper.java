package com.reptile.entity;

import java.io.Serializable;
import java.util.Date;

public class AcademicPaper implements Serializable {
    private Integer articleId;

    private String articleTitle;

    private String articleKeyword;

    private String author;

    private Date updateTime;

    private String createTime;

    private String source;

    private Integer contentType;

    private String contentExcerpt;

    private String imagePath;

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

    private byte[] detailsTxt;

    private byte[] detailsDiv;

    private String reference;

    private static final long serialVersionUID = 1L;

    public Integer getArticleId() {
        return articleId;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleKeyword=").append(articleKeyword);
        sb.append(", author=").append(author);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", source=").append(source);
        sb.append(", contentType=").append(contentType);
        sb.append(", contentExcerpt=").append(contentExcerpt);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", postingName=").append(postingName);
        sb.append(", articleTitleE=").append(articleTitleE);
        sb.append(", wordCount=").append(wordCount);
        sb.append(", contentExcerptE=").append(contentExcerptE);
        sb.append(", pdfPath=").append(pdfPath);
        sb.append(", articleKeywordE=").append(articleKeywordE);
        sb.append(", authorE=").append(authorE);
        sb.append(", siteNumber=").append(siteNumber);
        sb.append(", seachKeyword=").append(seachKeyword);
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", detailsTxt=").append(detailsTxt);
        sb.append(", detailsDiv=").append(detailsDiv);
        sb.append(", reference=").append(reference);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}