package com.reptile.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaperExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Integer value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Integer value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Integer value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Integer value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Integer> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Integer> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Integer value1, Integer value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordIsNull() {
            addCriterion("article_keyword is null");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordIsNotNull() {
            addCriterion("article_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEqualTo(String value) {
            addCriterion("article_keyword =", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordNotEqualTo(String value) {
            addCriterion("article_keyword <>", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordGreaterThan(String value) {
            addCriterion("article_keyword >", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("article_keyword >=", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordLessThan(String value) {
            addCriterion("article_keyword <", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordLessThanOrEqualTo(String value) {
            addCriterion("article_keyword <=", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordLike(String value) {
            addCriterion("article_keyword like", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordNotLike(String value) {
            addCriterion("article_keyword not like", value, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordIn(List<String> values) {
            addCriterion("article_keyword in", values, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordNotIn(List<String> values) {
            addCriterion("article_keyword not in", values, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordBetween(String value1, String value2) {
            addCriterion("article_keyword between", value1, value2, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordNotBetween(String value1, String value2) {
            addCriterion("article_keyword not between", value1, value2, "articleKeyword");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andContentTypeIsNull() {
            addCriterion("content_type is null");
            return (Criteria) this;
        }

        public Criteria andContentTypeIsNotNull() {
            addCriterion("content_type is not null");
            return (Criteria) this;
        }

        public Criteria andContentTypeEqualTo(Integer value) {
            addCriterion("content_type =", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotEqualTo(Integer value) {
            addCriterion("content_type <>", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThan(Integer value) {
            addCriterion("content_type >", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_type >=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThan(Integer value) {
            addCriterion("content_type <", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("content_type <=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeIn(List<Integer> values) {
            addCriterion("content_type in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotIn(List<Integer> values) {
            addCriterion("content_type not in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeBetween(Integer value1, Integer value2) {
            addCriterion("content_type between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("content_type not between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentExcerptIsNull() {
            addCriterion("content_excerpt is null");
            return (Criteria) this;
        }

        public Criteria andContentExcerptIsNotNull() {
            addCriterion("content_excerpt is not null");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEqualTo(String value) {
            addCriterion("content_excerpt =", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptNotEqualTo(String value) {
            addCriterion("content_excerpt <>", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptGreaterThan(String value) {
            addCriterion("content_excerpt >", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptGreaterThanOrEqualTo(String value) {
            addCriterion("content_excerpt >=", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptLessThan(String value) {
            addCriterion("content_excerpt <", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptLessThanOrEqualTo(String value) {
            addCriterion("content_excerpt <=", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptLike(String value) {
            addCriterion("content_excerpt like", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptNotLike(String value) {
            addCriterion("content_excerpt not like", value, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptIn(List<String> values) {
            addCriterion("content_excerpt in", values, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptNotIn(List<String> values) {
            addCriterion("content_excerpt not in", values, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptBetween(String value1, String value2) {
            addCriterion("content_excerpt between", value1, value2, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andContentExcerptNotBetween(String value1, String value2) {
            addCriterion("content_excerpt not between", value1, value2, "contentExcerpt");
            return (Criteria) this;
        }

        public Criteria andPostingNameIsNull() {
            addCriterion("posting_name is null");
            return (Criteria) this;
        }

        public Criteria andPostingNameIsNotNull() {
            addCriterion("posting_name is not null");
            return (Criteria) this;
        }

        public Criteria andPostingNameEqualTo(String value) {
            addCriterion("posting_name =", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameNotEqualTo(String value) {
            addCriterion("posting_name <>", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameGreaterThan(String value) {
            addCriterion("posting_name >", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameGreaterThanOrEqualTo(String value) {
            addCriterion("posting_name >=", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameLessThan(String value) {
            addCriterion("posting_name <", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameLessThanOrEqualTo(String value) {
            addCriterion("posting_name <=", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameLike(String value) {
            addCriterion("posting_name like", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameNotLike(String value) {
            addCriterion("posting_name not like", value, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameIn(List<String> values) {
            addCriterion("posting_name in", values, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameNotIn(List<String> values) {
            addCriterion("posting_name not in", values, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameBetween(String value1, String value2) {
            addCriterion("posting_name between", value1, value2, "postingName");
            return (Criteria) this;
        }

        public Criteria andPostingNameNotBetween(String value1, String value2) {
            addCriterion("posting_name not between", value1, value2, "postingName");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEIsNull() {
            addCriterion("article_title_e is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEIsNotNull() {
            addCriterion("article_title_e is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEEqualTo(String value) {
            addCriterion("article_title_e =", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleENotEqualTo(String value) {
            addCriterion("article_title_e <>", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEGreaterThan(String value) {
            addCriterion("article_title_e >", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEGreaterThanOrEqualTo(String value) {
            addCriterion("article_title_e >=", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleELessThan(String value) {
            addCriterion("article_title_e <", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleELessThanOrEqualTo(String value) {
            addCriterion("article_title_e <=", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleELike(String value) {
            addCriterion("article_title_e like", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleENotLike(String value) {
            addCriterion("article_title_e not like", value, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEIn(List<String> values) {
            addCriterion("article_title_e in", values, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleENotIn(List<String> values) {
            addCriterion("article_title_e not in", values, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEBetween(String value1, String value2) {
            addCriterion("article_title_e between", value1, value2, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andArticleTitleENotBetween(String value1, String value2) {
            addCriterion("article_title_e not between", value1, value2, "articleTitleE");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNull() {
            addCriterion("word_count is null");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNotNull() {
            addCriterion("word_count is not null");
            return (Criteria) this;
        }

        public Criteria andWordCountEqualTo(Integer value) {
            addCriterion("word_count =", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotEqualTo(Integer value) {
            addCriterion("word_count <>", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThan(Integer value) {
            addCriterion("word_count >", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_count >=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThan(Integer value) {
            addCriterion("word_count <", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThanOrEqualTo(Integer value) {
            addCriterion("word_count <=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountIn(List<Integer> values) {
            addCriterion("word_count in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotIn(List<Integer> values) {
            addCriterion("word_count not in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountBetween(Integer value1, Integer value2) {
            addCriterion("word_count between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotBetween(Integer value1, Integer value2) {
            addCriterion("word_count not between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEIsNull() {
            addCriterion("content_excerpt_e is null");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEIsNotNull() {
            addCriterion("content_excerpt_e is not null");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEEqualTo(String value) {
            addCriterion("content_excerpt_e =", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptENotEqualTo(String value) {
            addCriterion("content_excerpt_e <>", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEGreaterThan(String value) {
            addCriterion("content_excerpt_e >", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEGreaterThanOrEqualTo(String value) {
            addCriterion("content_excerpt_e >=", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptELessThan(String value) {
            addCriterion("content_excerpt_e <", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptELessThanOrEqualTo(String value) {
            addCriterion("content_excerpt_e <=", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptELike(String value) {
            addCriterion("content_excerpt_e like", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptENotLike(String value) {
            addCriterion("content_excerpt_e not like", value, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEIn(List<String> values) {
            addCriterion("content_excerpt_e in", values, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptENotIn(List<String> values) {
            addCriterion("content_excerpt_e not in", values, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptEBetween(String value1, String value2) {
            addCriterion("content_excerpt_e between", value1, value2, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andContentExcerptENotBetween(String value1, String value2) {
            addCriterion("content_excerpt_e not between", value1, value2, "contentExcerptE");
            return (Criteria) this;
        }

        public Criteria andPdfPathIsNull() {
            addCriterion("pdf_path is null");
            return (Criteria) this;
        }

        public Criteria andPdfPathIsNotNull() {
            addCriterion("pdf_path is not null");
            return (Criteria) this;
        }

        public Criteria andPdfPathEqualTo(String value) {
            addCriterion("pdf_path =", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathNotEqualTo(String value) {
            addCriterion("pdf_path <>", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathGreaterThan(String value) {
            addCriterion("pdf_path >", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathGreaterThanOrEqualTo(String value) {
            addCriterion("pdf_path >=", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathLessThan(String value) {
            addCriterion("pdf_path <", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathLessThanOrEqualTo(String value) {
            addCriterion("pdf_path <=", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathLike(String value) {
            addCriterion("pdf_path like", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathNotLike(String value) {
            addCriterion("pdf_path not like", value, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathIn(List<String> values) {
            addCriterion("pdf_path in", values, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathNotIn(List<String> values) {
            addCriterion("pdf_path not in", values, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathBetween(String value1, String value2) {
            addCriterion("pdf_path between", value1, value2, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andPdfPathNotBetween(String value1, String value2) {
            addCriterion("pdf_path not between", value1, value2, "pdfPath");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEIsNull() {
            addCriterion("article_keyword_e is null");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEIsNotNull() {
            addCriterion("article_keyword_e is not null");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEEqualTo(String value) {
            addCriterion("article_keyword_e =", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordENotEqualTo(String value) {
            addCriterion("article_keyword_e <>", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEGreaterThan(String value) {
            addCriterion("article_keyword_e >", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEGreaterThanOrEqualTo(String value) {
            addCriterion("article_keyword_e >=", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordELessThan(String value) {
            addCriterion("article_keyword_e <", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordELessThanOrEqualTo(String value) {
            addCriterion("article_keyword_e <=", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordELike(String value) {
            addCriterion("article_keyword_e like", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordENotLike(String value) {
            addCriterion("article_keyword_e not like", value, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEIn(List<String> values) {
            addCriterion("article_keyword_e in", values, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordENotIn(List<String> values) {
            addCriterion("article_keyword_e not in", values, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordEBetween(String value1, String value2) {
            addCriterion("article_keyword_e between", value1, value2, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andArticleKeywordENotBetween(String value1, String value2) {
            addCriterion("article_keyword_e not between", value1, value2, "articleKeywordE");
            return (Criteria) this;
        }

        public Criteria andAuthorEIsNull() {
            addCriterion("author_e is null");
            return (Criteria) this;
        }

        public Criteria andAuthorEIsNotNull() {
            addCriterion("author_e is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEEqualTo(String value) {
            addCriterion("author_e =", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorENotEqualTo(String value) {
            addCriterion("author_e <>", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorEGreaterThan(String value) {
            addCriterion("author_e >", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorEGreaterThanOrEqualTo(String value) {
            addCriterion("author_e >=", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorELessThan(String value) {
            addCriterion("author_e <", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorELessThanOrEqualTo(String value) {
            addCriterion("author_e <=", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorELike(String value) {
            addCriterion("author_e like", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorENotLike(String value) {
            addCriterion("author_e not like", value, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorEIn(List<String> values) {
            addCriterion("author_e in", values, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorENotIn(List<String> values) {
            addCriterion("author_e not in", values, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorEBetween(String value1, String value2) {
            addCriterion("author_e between", value1, value2, "authorE");
            return (Criteria) this;
        }

        public Criteria andAuthorENotBetween(String value1, String value2) {
            addCriterion("author_e not between", value1, value2, "authorE");
            return (Criteria) this;
        }

        public Criteria andSiteNumberIsNull() {
            addCriterion("site_number is null");
            return (Criteria) this;
        }

        public Criteria andSiteNumberIsNotNull() {
            addCriterion("site_number is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNumberEqualTo(Integer value) {
            addCriterion("site_number =", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberNotEqualTo(Integer value) {
            addCriterion("site_number <>", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberGreaterThan(Integer value) {
            addCriterion("site_number >", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("site_number >=", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberLessThan(Integer value) {
            addCriterion("site_number <", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberLessThanOrEqualTo(Integer value) {
            addCriterion("site_number <=", value, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberIn(List<Integer> values) {
            addCriterion("site_number in", values, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberNotIn(List<Integer> values) {
            addCriterion("site_number not in", values, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberBetween(Integer value1, Integer value2) {
            addCriterion("site_number between", value1, value2, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSiteNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("site_number not between", value1, value2, "siteNumber");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordIsNull() {
            addCriterion("seach_keyword is null");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordIsNotNull() {
            addCriterion("seach_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordEqualTo(String value) {
            addCriterion("seach_keyword =", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordNotEqualTo(String value) {
            addCriterion("seach_keyword <>", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordGreaterThan(String value) {
            addCriterion("seach_keyword >", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("seach_keyword >=", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordLessThan(String value) {
            addCriterion("seach_keyword <", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordLessThanOrEqualTo(String value) {
            addCriterion("seach_keyword <=", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordLike(String value) {
            addCriterion("seach_keyword like", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordNotLike(String value) {
            addCriterion("seach_keyword not like", value, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordIn(List<String> values) {
            addCriterion("seach_keyword in", values, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordNotIn(List<String> values) {
            addCriterion("seach_keyword not in", values, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordBetween(String value1, String value2) {
            addCriterion("seach_keyword between", value1, value2, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andSeachKeywordNotBetween(String value1, String value2) {
            addCriterion("seach_keyword not between", value1, value2, "seachKeyword");
            return (Criteria) this;
        }

        public Criteria andPublicationDateIsNull() {
            addCriterion("publication_date is null");
            return (Criteria) this;
        }

        public Criteria andPublicationDateIsNotNull() {
            addCriterion("publication_date is not null");
            return (Criteria) this;
        }

        public Criteria andPublicationDateEqualTo(String value) {
            addCriterion("publication_date =", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateNotEqualTo(String value) {
            addCriterion("publication_date <>", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateGreaterThan(String value) {
            addCriterion("publication_date >", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateGreaterThanOrEqualTo(String value) {
            addCriterion("publication_date >=", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateLessThan(String value) {
            addCriterion("publication_date <", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateLessThanOrEqualTo(String value) {
            addCriterion("publication_date <=", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateLike(String value) {
            addCriterion("publication_date like", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateNotLike(String value) {
            addCriterion("publication_date not like", value, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateIn(List<String> values) {
            addCriterion("publication_date in", values, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateNotIn(List<String> values) {
            addCriterion("publication_date not in", values, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateBetween(String value1, String value2) {
            addCriterion("publication_date between", value1, value2, "publicationDate");
            return (Criteria) this;
        }

        public Criteria andPublicationDateNotBetween(String value1, String value2) {
            addCriterion("publication_date not between", value1, value2, "publicationDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}