package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TBorrowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBorrowExample() {
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

        public Criteria andSIdIsNull() {
            addCriterion("s_id is null");
            return (Criteria) this;
        }

        public Criteria andSIdIsNotNull() {
            addCriterion("s_id is not null");
            return (Criteria) this;
        }

        public Criteria andSIdEqualTo(String value) {
            addCriterion("s_id =", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotEqualTo(String value) {
            addCriterion("s_id <>", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThan(String value) {
            addCriterion("s_id >", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdGreaterThanOrEqualTo(String value) {
            addCriterion("s_id >=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThan(String value) {
            addCriterion("s_id <", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLessThanOrEqualTo(String value) {
            addCriterion("s_id <=", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdLike(String value) {
            addCriterion("s_id like", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotLike(String value) {
            addCriterion("s_id not like", value, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdIn(List<String> values) {
            addCriterion("s_id in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotIn(List<String> values) {
            addCriterion("s_id not in", values, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdBetween(String value1, String value2) {
            addCriterion("s_id between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSIdNotBetween(String value1, String value2) {
            addCriterion("s_id not between", value1, value2, "sId");
            return (Criteria) this;
        }

        public Criteria andSmIsNull() {
            addCriterion("sm is null");
            return (Criteria) this;
        }

        public Criteria andSmIsNotNull() {
            addCriterion("sm is not null");
            return (Criteria) this;
        }

        public Criteria andSmEqualTo(String value) {
            addCriterion("sm =", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotEqualTo(String value) {
            addCriterion("sm <>", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThan(String value) {
            addCriterion("sm >", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmGreaterThanOrEqualTo(String value) {
            addCriterion("sm >=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThan(String value) {
            addCriterion("sm <", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLessThanOrEqualTo(String value) {
            addCriterion("sm <=", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmLike(String value) {
            addCriterion("sm like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotLike(String value) {
            addCriterion("sm not like", value, "sm");
            return (Criteria) this;
        }

        public Criteria andSmIn(List<String> values) {
            addCriterion("sm in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotIn(List<String> values) {
            addCriterion("sm not in", values, "sm");
            return (Criteria) this;
        }

        public Criteria andSmBetween(String value1, String value2) {
            addCriterion("sm between", value1, value2, "sm");
            return (Criteria) this;
        }

        public Criteria andSmNotBetween(String value1, String value2) {
            addCriterion("sm not between", value1, value2, "sm");
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

        public Criteria andLxfsIsNull() {
            addCriterion("lxfs is null");
            return (Criteria) this;
        }

        public Criteria andLxfsIsNotNull() {
            addCriterion("lxfs is not null");
            return (Criteria) this;
        }

        public Criteria andLxfsEqualTo(String value) {
            addCriterion("lxfs =", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsNotEqualTo(String value) {
            addCriterion("lxfs <>", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsGreaterThan(String value) {
            addCriterion("lxfs >", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsGreaterThanOrEqualTo(String value) {
            addCriterion("lxfs >=", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsLessThan(String value) {
            addCriterion("lxfs <", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsLessThanOrEqualTo(String value) {
            addCriterion("lxfs <=", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsLike(String value) {
            addCriterion("lxfs like", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsNotLike(String value) {
            addCriterion("lxfs not like", value, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsIn(List<String> values) {
            addCriterion("lxfs in", values, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsNotIn(List<String> values) {
            addCriterion("lxfs not in", values, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsBetween(String value1, String value2) {
            addCriterion("lxfs between", value1, value2, "lxfs");
            return (Criteria) this;
        }

        public Criteria andLxfsNotBetween(String value1, String value2) {
            addCriterion("lxfs not between", value1, value2, "lxfs");
            return (Criteria) this;
        }

        public Criteria andJyrqIsNull() {
            addCriterion("jyrq is null");
            return (Criteria) this;
        }

        public Criteria andJyrqIsNotNull() {
            addCriterion("jyrq is not null");
            return (Criteria) this;
        }

        public Criteria andJyrqEqualTo(String value) {
            addCriterion("jyrq =", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotEqualTo(String value) {
            addCriterion("jyrq <>", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqGreaterThan(String value) {
            addCriterion("jyrq >", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqGreaterThanOrEqualTo(String value) {
            addCriterion("jyrq >=", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqLessThan(String value) {
            addCriterion("jyrq <", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqLessThanOrEqualTo(String value) {
            addCriterion("jyrq <=", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqLike(String value) {
            addCriterion("jyrq like", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotLike(String value) {
            addCriterion("jyrq not like", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqIn(List<String> values) {
            addCriterion("jyrq in", values, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotIn(List<String> values) {
            addCriterion("jyrq not in", values, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqBetween(String value1, String value2) {
            addCriterion("jyrq between", value1, value2, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotBetween(String value1, String value2) {
            addCriterion("jyrq not between", value1, value2, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqIsNull() {
            addCriterion("jhghrq is null");
            return (Criteria) this;
        }

        public Criteria andJhghrqIsNotNull() {
            addCriterion("jhghrq is not null");
            return (Criteria) this;
        }

        public Criteria andJhghrqEqualTo(String value) {
            addCriterion("jhghrq =", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotEqualTo(String value) {
            addCriterion("jhghrq <>", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqGreaterThan(String value) {
            addCriterion("jhghrq >", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqGreaterThanOrEqualTo(String value) {
            addCriterion("jhghrq >=", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqLessThan(String value) {
            addCriterion("jhghrq <", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqLessThanOrEqualTo(String value) {
            addCriterion("jhghrq <=", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqLike(String value) {
            addCriterion("jhghrq like", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotLike(String value) {
            addCriterion("jhghrq not like", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqIn(List<String> values) {
            addCriterion("jhghrq in", values, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotIn(List<String> values) {
            addCriterion("jhghrq not in", values, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqBetween(String value1, String value2) {
            addCriterion("jhghrq between", value1, value2, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotBetween(String value1, String value2) {
            addCriterion("jhghrq not between", value1, value2, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andXjrqIsNull() {
            addCriterion("xjrq is null");
            return (Criteria) this;
        }

        public Criteria andXjrqIsNotNull() {
            addCriterion("xjrq is not null");
            return (Criteria) this;
        }

        public Criteria andXjrqEqualTo(String value) {
            addCriterion("xjrq =", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotEqualTo(String value) {
            addCriterion("xjrq <>", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqGreaterThan(String value) {
            addCriterion("xjrq >", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqGreaterThanOrEqualTo(String value) {
            addCriterion("xjrq >=", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqLessThan(String value) {
            addCriterion("xjrq <", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqLessThanOrEqualTo(String value) {
            addCriterion("xjrq <=", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqLike(String value) {
            addCriterion("xjrq like", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotLike(String value) {
            addCriterion("xjrq not like", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqIn(List<String> values) {
            addCriterion("xjrq in", values, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotIn(List<String> values) {
            addCriterion("xjrq not in", values, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqBetween(String value1, String value2) {
            addCriterion("xjrq between", value1, value2, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotBetween(String value1, String value2) {
            addCriterion("xjrq not between", value1, value2, "xjrq");
            return (Criteria) this;
        }

        public Criteria andGhrqIsNull() {
            addCriterion("ghrq is null");
            return (Criteria) this;
        }

        public Criteria andGhrqIsNotNull() {
            addCriterion("ghrq is not null");
            return (Criteria) this;
        }

        public Criteria andGhrqEqualTo(String value) {
            addCriterion("ghrq =", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotEqualTo(String value) {
            addCriterion("ghrq <>", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqGreaterThan(String value) {
            addCriterion("ghrq >", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqGreaterThanOrEqualTo(String value) {
            addCriterion("ghrq >=", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqLessThan(String value) {
            addCriterion("ghrq <", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqLessThanOrEqualTo(String value) {
            addCriterion("ghrq <=", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqLike(String value) {
            addCriterion("ghrq like", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotLike(String value) {
            addCriterion("ghrq not like", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqIn(List<String> values) {
            addCriterion("ghrq in", values, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotIn(List<String> values) {
            addCriterion("ghrq not in", values, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqBetween(String value1, String value2) {
            addCriterion("ghrq between", value1, value2, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotBetween(String value1, String value2) {
            addCriterion("ghrq not between", value1, value2, "ghrq");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andJyztIsNull() {
            addCriterion("jyzt is null");
            return (Criteria) this;
        }

        public Criteria andJyztIsNotNull() {
            addCriterion("jyzt is not null");
            return (Criteria) this;
        }

        public Criteria andJyztEqualTo(Integer value) {
            addCriterion("jyzt =", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztNotEqualTo(Integer value) {
            addCriterion("jyzt <>", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztGreaterThan(Integer value) {
            addCriterion("jyzt >", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztGreaterThanOrEqualTo(Integer value) {
            addCriterion("jyzt >=", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztLessThan(Integer value) {
            addCriterion("jyzt <", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztLessThanOrEqualTo(Integer value) {
            addCriterion("jyzt <=", value, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztIn(List<Integer> values) {
            addCriterion("jyzt in", values, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztNotIn(List<Integer> values) {
            addCriterion("jyzt not in", values, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztBetween(Integer value1, Integer value2) {
            addCriterion("jyzt between", value1, value2, "jyzt");
            return (Criteria) this;
        }

        public Criteria andJyztNotBetween(Integer value1, Integer value2) {
            addCriterion("jyzt not between", value1, value2, "jyzt");
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