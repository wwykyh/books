package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TBookNewsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBookNewsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBookIsbnIsNull() {
            addCriterion("book_isbn is null");
            return (Criteria) this;
        }

        public Criteria andBookIsbnIsNotNull() {
            addCriterion("book_isbn is not null");
            return (Criteria) this;
        }

        public Criteria andBookIsbnEqualTo(String value) {
            addCriterion("book_isbn =", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnNotEqualTo(String value) {
            addCriterion("book_isbn <>", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnGreaterThan(String value) {
            addCriterion("book_isbn >", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("book_isbn >=", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnLessThan(String value) {
            addCriterion("book_isbn <", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnLessThanOrEqualTo(String value) {
            addCriterion("book_isbn <=", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnLike(String value) {
            addCriterion("book_isbn like", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnNotLike(String value) {
            addCriterion("book_isbn not like", value, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnIn(List<String> values) {
            addCriterion("book_isbn in", values, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnNotIn(List<String> values) {
            addCriterion("book_isbn not in", values, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnBetween(String value1, String value2) {
            addCriterion("book_isbn between", value1, value2, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andBookIsbnNotBetween(String value1, String value2) {
            addCriterion("book_isbn not between", value1, value2, "bookIsbn");
            return (Criteria) this;
        }

        public Criteria andNewsNameIsNull() {
            addCriterion("news_name is null");
            return (Criteria) this;
        }

        public Criteria andNewsNameIsNotNull() {
            addCriterion("news_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewsNameEqualTo(String value) {
            addCriterion("news_name =", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotEqualTo(String value) {
            addCriterion("news_name <>", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameGreaterThan(String value) {
            addCriterion("news_name >", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameGreaterThanOrEqualTo(String value) {
            addCriterion("news_name >=", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLessThan(String value) {
            addCriterion("news_name <", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLessThanOrEqualTo(String value) {
            addCriterion("news_name <=", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameLike(String value) {
            addCriterion("news_name like", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotLike(String value) {
            addCriterion("news_name not like", value, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameIn(List<String> values) {
            addCriterion("news_name in", values, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotIn(List<String> values) {
            addCriterion("news_name not in", values, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameBetween(String value1, String value2) {
            addCriterion("news_name between", value1, value2, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsNameNotBetween(String value1, String value2) {
            addCriterion("news_name not between", value1, value2, "newsName");
            return (Criteria) this;
        }

        public Criteria andNewsDateIsNull() {
            addCriterion("news_date is null");
            return (Criteria) this;
        }

        public Criteria andNewsDateIsNotNull() {
            addCriterion("news_date is not null");
            return (Criteria) this;
        }

        public Criteria andNewsDateEqualTo(String value) {
            addCriterion("news_date =", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotEqualTo(String value) {
            addCriterion("news_date <>", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateGreaterThan(String value) {
            addCriterion("news_date >", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateGreaterThanOrEqualTo(String value) {
            addCriterion("news_date >=", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLessThan(String value) {
            addCriterion("news_date <", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLessThanOrEqualTo(String value) {
            addCriterion("news_date <=", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateLike(String value) {
            addCriterion("news_date like", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotLike(String value) {
            addCriterion("news_date not like", value, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateIn(List<String> values) {
            addCriterion("news_date in", values, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotIn(List<String> values) {
            addCriterion("news_date not in", values, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateBetween(String value1, String value2) {
            addCriterion("news_date between", value1, value2, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsDateNotBetween(String value1, String value2) {
            addCriterion("news_date not between", value1, value2, "newsDate");
            return (Criteria) this;
        }

        public Criteria andNewsTypeIsNull() {
            addCriterion("news_type is null");
            return (Criteria) this;
        }

        public Criteria andNewsTypeIsNotNull() {
            addCriterion("news_type is not null");
            return (Criteria) this;
        }

        public Criteria andNewsTypeEqualTo(String value) {
            addCriterion("news_type =", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeNotEqualTo(String value) {
            addCriterion("news_type <>", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeGreaterThan(String value) {
            addCriterion("news_type >", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("news_type >=", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeLessThan(String value) {
            addCriterion("news_type <", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeLessThanOrEqualTo(String value) {
            addCriterion("news_type <=", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeLike(String value) {
            addCriterion("news_type like", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeNotLike(String value) {
            addCriterion("news_type not like", value, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeIn(List<String> values) {
            addCriterion("news_type in", values, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeNotIn(List<String> values) {
            addCriterion("news_type not in", values, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeBetween(String value1, String value2) {
            addCriterion("news_type between", value1, value2, "newsType");
            return (Criteria) this;
        }

        public Criteria andNewsTypeNotBetween(String value1, String value2) {
            addCriterion("news_type not between", value1, value2, "newsType");
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