package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TCompensateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCompensateExample() {
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

        public Criteria andShIsNull() {
            addCriterion("sh is null");
            return (Criteria) this;
        }

        public Criteria andShIsNotNull() {
            addCriterion("sh is not null");
            return (Criteria) this;
        }

        public Criteria andShEqualTo(Integer value) {
            addCriterion("sh =", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShNotEqualTo(Integer value) {
            addCriterion("sh <>", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShGreaterThan(Integer value) {
            addCriterion("sh >", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShGreaterThanOrEqualTo(Integer value) {
            addCriterion("sh >=", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShLessThan(Integer value) {
            addCriterion("sh <", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShLessThanOrEqualTo(Integer value) {
            addCriterion("sh <=", value, "sh");
            return (Criteria) this;
        }

        public Criteria andShIn(List<Integer> values) {
            addCriterion("sh in", values, "sh");
            return (Criteria) this;
        }

        public Criteria andShNotIn(List<Integer> values) {
            addCriterion("sh not in", values, "sh");
            return (Criteria) this;
        }

        public Criteria andShBetween(Integer value1, Integer value2) {
            addCriterion("sh between", value1, value2, "sh");
            return (Criteria) this;
        }

        public Criteria andShNotBetween(Integer value1, Integer value2) {
            addCriterion("sh not between", value1, value2, "sh");
            return (Criteria) this;
        }

        public Criteria andPcdateIsNull() {
            addCriterion("pcdate is null");
            return (Criteria) this;
        }

        public Criteria andPcdateIsNotNull() {
            addCriterion("pcdate is not null");
            return (Criteria) this;
        }

        public Criteria andPcdateEqualTo(String value) {
            addCriterion("pcdate =", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateNotEqualTo(String value) {
            addCriterion("pcdate <>", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateGreaterThan(String value) {
            addCriterion("pcdate >", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateGreaterThanOrEqualTo(String value) {
            addCriterion("pcdate >=", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateLessThan(String value) {
            addCriterion("pcdate <", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateLessThanOrEqualTo(String value) {
            addCriterion("pcdate <=", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateLike(String value) {
            addCriterion("pcdate like", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateNotLike(String value) {
            addCriterion("pcdate not like", value, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateIn(List<String> values) {
            addCriterion("pcdate in", values, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateNotIn(List<String> values) {
            addCriterion("pcdate not in", values, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateBetween(String value1, String value2) {
            addCriterion("pcdate between", value1, value2, "pcdate");
            return (Criteria) this;
        }

        public Criteria andPcdateNotBetween(String value1, String value2) {
            addCriterion("pcdate not between", value1, value2, "pcdate");
            return (Criteria) this;
        }

        public Criteria andIspcIsNull() {
            addCriterion("ispc is null");
            return (Criteria) this;
        }

        public Criteria andIspcIsNotNull() {
            addCriterion("ispc is not null");
            return (Criteria) this;
        }

        public Criteria andIspcEqualTo(Integer value) {
            addCriterion("ispc =", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcNotEqualTo(Integer value) {
            addCriterion("ispc <>", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcGreaterThan(Integer value) {
            addCriterion("ispc >", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcGreaterThanOrEqualTo(Integer value) {
            addCriterion("ispc >=", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcLessThan(Integer value) {
            addCriterion("ispc <", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcLessThanOrEqualTo(Integer value) {
            addCriterion("ispc <=", value, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcIn(List<Integer> values) {
            addCriterion("ispc in", values, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcNotIn(List<Integer> values) {
            addCriterion("ispc not in", values, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcBetween(Integer value1, Integer value2) {
            addCriterion("ispc between", value1, value2, "ispc");
            return (Criteria) this;
        }

        public Criteria andIspcNotBetween(Integer value1, Integer value2) {
            addCriterion("ispc not between", value1, value2, "ispc");
            return (Criteria) this;
        }

        public Criteria andIscountIsNull() {
            addCriterion("iscount is null");
            return (Criteria) this;
        }

        public Criteria andIscountIsNotNull() {
            addCriterion("iscount is not null");
            return (Criteria) this;
        }

        public Criteria andIscountEqualTo(Integer value) {
            addCriterion("iscount =", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountNotEqualTo(Integer value) {
            addCriterion("iscount <>", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountGreaterThan(Integer value) {
            addCriterion("iscount >", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("iscount >=", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountLessThan(Integer value) {
            addCriterion("iscount <", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountLessThanOrEqualTo(Integer value) {
            addCriterion("iscount <=", value, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountIn(List<Integer> values) {
            addCriterion("iscount in", values, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountNotIn(List<Integer> values) {
            addCriterion("iscount not in", values, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountBetween(Integer value1, Integer value2) {
            addCriterion("iscount between", value1, value2, "iscount");
            return (Criteria) this;
        }

        public Criteria andIscountNotBetween(Integer value1, Integer value2) {
            addCriterion("iscount not between", value1, value2, "iscount");
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