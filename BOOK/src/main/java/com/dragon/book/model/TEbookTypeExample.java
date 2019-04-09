package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TEbookTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TEbookTypeExample() {
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

        public Criteria andEbookTypeidIsNull() {
            addCriterion("ebook_typeid is null");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidIsNotNull() {
            addCriterion("ebook_typeid is not null");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidEqualTo(Integer value) {
            addCriterion("ebook_typeid =", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidNotEqualTo(Integer value) {
            addCriterion("ebook_typeid <>", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidGreaterThan(Integer value) {
            addCriterion("ebook_typeid >", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ebook_typeid >=", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidLessThan(Integer value) {
            addCriterion("ebook_typeid <", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("ebook_typeid <=", value, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidIn(List<Integer> values) {
            addCriterion("ebook_typeid in", values, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidNotIn(List<Integer> values) {
            addCriterion("ebook_typeid not in", values, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidBetween(Integer value1, Integer value2) {
            addCriterion("ebook_typeid between", value1, value2, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("ebook_typeid not between", value1, value2, "ebookTypeid");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcIsNull() {
            addCriterion("ebook_typemc is null");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcIsNotNull() {
            addCriterion("ebook_typemc is not null");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcEqualTo(String value) {
            addCriterion("ebook_typemc =", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcNotEqualTo(String value) {
            addCriterion("ebook_typemc <>", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcGreaterThan(String value) {
            addCriterion("ebook_typemc >", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcGreaterThanOrEqualTo(String value) {
            addCriterion("ebook_typemc >=", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcLessThan(String value) {
            addCriterion("ebook_typemc <", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcLessThanOrEqualTo(String value) {
            addCriterion("ebook_typemc <=", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcLike(String value) {
            addCriterion("ebook_typemc like", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcNotLike(String value) {
            addCriterion("ebook_typemc not like", value, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcIn(List<String> values) {
            addCriterion("ebook_typemc in", values, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcNotIn(List<String> values) {
            addCriterion("ebook_typemc not in", values, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcBetween(String value1, String value2) {
            addCriterion("ebook_typemc between", value1, value2, "ebookTypemc");
            return (Criteria) this;
        }

        public Criteria andEbookTypemcNotBetween(String value1, String value2) {
            addCriterion("ebook_typemc not between", value1, value2, "ebookTypemc");
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