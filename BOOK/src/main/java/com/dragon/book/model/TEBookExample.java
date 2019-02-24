package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TEBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TEBookExample() {
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

        public Criteria andEBookIdIsNull() {
            addCriterion("e_book_id is null");
            return (Criteria) this;
        }

        public Criteria andEBookIdIsNotNull() {
            addCriterion("e_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andEBookIdEqualTo(String value) {
            addCriterion("e_book_id =", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdNotEqualTo(String value) {
            addCriterion("e_book_id <>", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdGreaterThan(String value) {
            addCriterion("e_book_id >", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdGreaterThanOrEqualTo(String value) {
            addCriterion("e_book_id >=", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdLessThan(String value) {
            addCriterion("e_book_id <", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdLessThanOrEqualTo(String value) {
            addCriterion("e_book_id <=", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdLike(String value) {
            addCriterion("e_book_id like", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdNotLike(String value) {
            addCriterion("e_book_id not like", value, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdIn(List<String> values) {
            addCriterion("e_book_id in", values, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdNotIn(List<String> values) {
            addCriterion("e_book_id not in", values, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdBetween(String value1, String value2) {
            addCriterion("e_book_id between", value1, value2, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookIdNotBetween(String value1, String value2) {
            addCriterion("e_book_id not between", value1, value2, "eBookId");
            return (Criteria) this;
        }

        public Criteria andEBookXmIsNull() {
            addCriterion("e_book_xm is null");
            return (Criteria) this;
        }

        public Criteria andEBookXmIsNotNull() {
            addCriterion("e_book_xm is not null");
            return (Criteria) this;
        }

        public Criteria andEBookXmEqualTo(String value) {
            addCriterion("e_book_xm =", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmNotEqualTo(String value) {
            addCriterion("e_book_xm <>", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmGreaterThan(String value) {
            addCriterion("e_book_xm >", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmGreaterThanOrEqualTo(String value) {
            addCriterion("e_book_xm >=", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmLessThan(String value) {
            addCriterion("e_book_xm <", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmLessThanOrEqualTo(String value) {
            addCriterion("e_book_xm <=", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmLike(String value) {
            addCriterion("e_book_xm like", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmNotLike(String value) {
            addCriterion("e_book_xm not like", value, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmIn(List<String> values) {
            addCriterion("e_book_xm in", values, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmNotIn(List<String> values) {
            addCriterion("e_book_xm not in", values, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmBetween(String value1, String value2) {
            addCriterion("e_book_xm between", value1, value2, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andEBookXmNotBetween(String value1, String value2) {
            addCriterion("e_book_xm not between", value1, value2, "eBookXm");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andWjdzIsNull() {
            addCriterion("wjdz is null");
            return (Criteria) this;
        }

        public Criteria andWjdzIsNotNull() {
            addCriterion("wjdz is not null");
            return (Criteria) this;
        }

        public Criteria andWjdzEqualTo(String value) {
            addCriterion("wjdz =", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzNotEqualTo(String value) {
            addCriterion("wjdz <>", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzGreaterThan(String value) {
            addCriterion("wjdz >", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzGreaterThanOrEqualTo(String value) {
            addCriterion("wjdz >=", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzLessThan(String value) {
            addCriterion("wjdz <", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzLessThanOrEqualTo(String value) {
            addCriterion("wjdz <=", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzLike(String value) {
            addCriterion("wjdz like", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzNotLike(String value) {
            addCriterion("wjdz not like", value, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzIn(List<String> values) {
            addCriterion("wjdz in", values, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzNotIn(List<String> values) {
            addCriterion("wjdz not in", values, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzBetween(String value1, String value2) {
            addCriterion("wjdz between", value1, value2, "wjdz");
            return (Criteria) this;
        }

        public Criteria andWjdzNotBetween(String value1, String value2) {
            addCriterion("wjdz not between", value1, value2, "wjdz");
            return (Criteria) this;
        }

        public Criteria andMsIsNull() {
            addCriterion("ms is null");
            return (Criteria) this;
        }

        public Criteria andMsIsNotNull() {
            addCriterion("ms is not null");
            return (Criteria) this;
        }

        public Criteria andMsEqualTo(String value) {
            addCriterion("ms =", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsNotEqualTo(String value) {
            addCriterion("ms <>", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsGreaterThan(String value) {
            addCriterion("ms >", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsGreaterThanOrEqualTo(String value) {
            addCriterion("ms >=", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsLessThan(String value) {
            addCriterion("ms <", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsLessThanOrEqualTo(String value) {
            addCriterion("ms <=", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsLike(String value) {
            addCriterion("ms like", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsNotLike(String value) {
            addCriterion("ms not like", value, "ms");
            return (Criteria) this;
        }

        public Criteria andMsIn(List<String> values) {
            addCriterion("ms in", values, "ms");
            return (Criteria) this;
        }

        public Criteria andMsNotIn(List<String> values) {
            addCriterion("ms not in", values, "ms");
            return (Criteria) this;
        }

        public Criteria andMsBetween(String value1, String value2) {
            addCriterion("ms between", value1, value2, "ms");
            return (Criteria) this;
        }

        public Criteria andMsNotBetween(String value1, String value2) {
            addCriterion("ms not between", value1, value2, "ms");
            return (Criteria) this;
        }

        public Criteria andScsjIsNull() {
            addCriterion("scsj is null");
            return (Criteria) this;
        }

        public Criteria andScsjIsNotNull() {
            addCriterion("scsj is not null");
            return (Criteria) this;
        }

        public Criteria andScsjEqualTo(String value) {
            addCriterion("scsj =", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotEqualTo(String value) {
            addCriterion("scsj <>", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThan(String value) {
            addCriterion("scsj >", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjGreaterThanOrEqualTo(String value) {
            addCriterion("scsj >=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThan(String value) {
            addCriterion("scsj <", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLessThanOrEqualTo(String value) {
            addCriterion("scsj <=", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjLike(String value) {
            addCriterion("scsj like", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotLike(String value) {
            addCriterion("scsj not like", value, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjIn(List<String> values) {
            addCriterion("scsj in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotIn(List<String> values) {
            addCriterion("scsj not in", values, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjBetween(String value1, String value2) {
            addCriterion("scsj between", value1, value2, "scsj");
            return (Criteria) this;
        }

        public Criteria andScsjNotBetween(String value1, String value2) {
            addCriterion("scsj not between", value1, value2, "scsj");
            return (Criteria) this;
        }

        public Criteria andXzsjIsNull() {
            addCriterion("xzsj is null");
            return (Criteria) this;
        }

        public Criteria andXzsjIsNotNull() {
            addCriterion("xzsj is not null");
            return (Criteria) this;
        }

        public Criteria andXzsjEqualTo(String value) {
            addCriterion("xzsj =", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjNotEqualTo(String value) {
            addCriterion("xzsj <>", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjGreaterThan(String value) {
            addCriterion("xzsj >", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjGreaterThanOrEqualTo(String value) {
            addCriterion("xzsj >=", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjLessThan(String value) {
            addCriterion("xzsj <", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjLessThanOrEqualTo(String value) {
            addCriterion("xzsj <=", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjLike(String value) {
            addCriterion("xzsj like", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjNotLike(String value) {
            addCriterion("xzsj not like", value, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjIn(List<String> values) {
            addCriterion("xzsj in", values, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjNotIn(List<String> values) {
            addCriterion("xzsj not in", values, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjBetween(String value1, String value2) {
            addCriterion("xzsj between", value1, value2, "xzsj");
            return (Criteria) this;
        }

        public Criteria andXzsjNotBetween(String value1, String value2) {
            addCriterion("xzsj not between", value1, value2, "xzsj");
            return (Criteria) this;
        }

        public Criteria andTszlIsNull() {
            addCriterion("tszl is null");
            return (Criteria) this;
        }

        public Criteria andTszlIsNotNull() {
            addCriterion("tszl is not null");
            return (Criteria) this;
        }

        public Criteria andTszlEqualTo(String value) {
            addCriterion("tszl =", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotEqualTo(String value) {
            addCriterion("tszl <>", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlGreaterThan(String value) {
            addCriterion("tszl >", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlGreaterThanOrEqualTo(String value) {
            addCriterion("tszl >=", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLessThan(String value) {
            addCriterion("tszl <", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLessThanOrEqualTo(String value) {
            addCriterion("tszl <=", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLike(String value) {
            addCriterion("tszl like", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotLike(String value) {
            addCriterion("tszl not like", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlIn(List<String> values) {
            addCriterion("tszl in", values, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotIn(List<String> values) {
            addCriterion("tszl not in", values, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlBetween(String value1, String value2) {
            addCriterion("tszl between", value1, value2, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotBetween(String value1, String value2) {
            addCriterion("tszl not between", value1, value2, "tszl");
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