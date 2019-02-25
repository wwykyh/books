package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBookExample() {
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

        public Criteria andIsbnIsNull() {
            addCriterion("isbn is null");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNotNull() {
            addCriterion("isbn is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnEqualTo(String value) {
            addCriterion("isbn =", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotEqualTo(String value) {
            addCriterion("isbn <>", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThan(String value) {
            addCriterion("isbn >", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("isbn >=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThan(String value) {
            addCriterion("isbn <", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThanOrEqualTo(String value) {
            addCriterion("isbn <=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLike(String value) {
            addCriterion("isbn like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotLike(String value) {
            addCriterion("isbn not like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnIn(List<String> values) {
            addCriterion("isbn in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotIn(List<String> values) {
            addCriterion("isbn not in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnBetween(String value1, String value2) {
            addCriterion("isbn between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotBetween(String value1, String value2) {
            addCriterion("isbn not between", value1, value2, "isbn");
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

        public Criteria andCbsmcIsNull() {
            addCriterion("cbsmc is null");
            return (Criteria) this;
        }

        public Criteria andCbsmcIsNotNull() {
            addCriterion("cbsmc is not null");
            return (Criteria) this;
        }

        public Criteria andCbsmcEqualTo(String value) {
            addCriterion("cbsmc =", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcNotEqualTo(String value) {
            addCriterion("cbsmc <>", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcGreaterThan(String value) {
            addCriterion("cbsmc >", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcGreaterThanOrEqualTo(String value) {
            addCriterion("cbsmc >=", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcLessThan(String value) {
            addCriterion("cbsmc <", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcLessThanOrEqualTo(String value) {
            addCriterion("cbsmc <=", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcLike(String value) {
            addCriterion("cbsmc like", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcNotLike(String value) {
            addCriterion("cbsmc not like", value, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcIn(List<String> values) {
            addCriterion("cbsmc in", values, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcNotIn(List<String> values) {
            addCriterion("cbsmc not in", values, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcBetween(String value1, String value2) {
            addCriterion("cbsmc between", value1, value2, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbsmcNotBetween(String value1, String value2) {
            addCriterion("cbsmc not between", value1, value2, "cbsmc");
            return (Criteria) this;
        }

        public Criteria andCbrqIsNull() {
            addCriterion("cbrq is null");
            return (Criteria) this;
        }

        public Criteria andCbrqIsNotNull() {
            addCriterion("cbrq is not null");
            return (Criteria) this;
        }

        public Criteria andCbrqEqualTo(String value) {
            addCriterion("cbrq =", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotEqualTo(String value) {
            addCriterion("cbrq <>", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqGreaterThan(String value) {
            addCriterion("cbrq >", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqGreaterThanOrEqualTo(String value) {
            addCriterion("cbrq >=", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqLessThan(String value) {
            addCriterion("cbrq <", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqLessThanOrEqualTo(String value) {
            addCriterion("cbrq <=", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqLike(String value) {
            addCriterion("cbrq like", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotLike(String value) {
            addCriterion("cbrq not like", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqIn(List<String> values) {
            addCriterion("cbrq in", values, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotIn(List<String> values) {
            addCriterion("cbrq not in", values, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqBetween(String value1, String value2) {
            addCriterion("cbrq between", value1, value2, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotBetween(String value1, String value2) {
            addCriterion("cbrq not between", value1, value2, "cbrq");
            return (Criteria) this;
        }

        public Criteria andZzIsNull() {
            addCriterion("zz is null");
            return (Criteria) this;
        }

        public Criteria andZzIsNotNull() {
            addCriterion("zz is not null");
            return (Criteria) this;
        }

        public Criteria andZzEqualTo(String value) {
            addCriterion("zz =", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzNotEqualTo(String value) {
            addCriterion("zz <>", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzGreaterThan(String value) {
            addCriterion("zz >", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzGreaterThanOrEqualTo(String value) {
            addCriterion("zz >=", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzLessThan(String value) {
            addCriterion("zz <", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzLessThanOrEqualTo(String value) {
            addCriterion("zz <=", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzLike(String value) {
            addCriterion("zz like", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzNotLike(String value) {
            addCriterion("zz not like", value, "zz");
            return (Criteria) this;
        }

        public Criteria andZzIn(List<String> values) {
            addCriterion("zz in", values, "zz");
            return (Criteria) this;
        }

        public Criteria andZzNotIn(List<String> values) {
            addCriterion("zz not in", values, "zz");
            return (Criteria) this;
        }

        public Criteria andZzBetween(String value1, String value2) {
            addCriterion("zz between", value1, value2, "zz");
            return (Criteria) this;
        }

        public Criteria andZzNotBetween(String value1, String value2) {
            addCriterion("zz not between", value1, value2, "zz");
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

        public Criteria andTsdlIsNull() {
            addCriterion("tsdl is null");
            return (Criteria) this;
        }

        public Criteria andTsdlIsNotNull() {
            addCriterion("tsdl is not null");
            return (Criteria) this;
        }

        public Criteria andTsdlEqualTo(String value) {
            addCriterion("tsdl =", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlNotEqualTo(String value) {
            addCriterion("tsdl <>", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlGreaterThan(String value) {
            addCriterion("tsdl >", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlGreaterThanOrEqualTo(String value) {
            addCriterion("tsdl >=", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlLessThan(String value) {
            addCriterion("tsdl <", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlLessThanOrEqualTo(String value) {
            addCriterion("tsdl <=", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlLike(String value) {
            addCriterion("tsdl like", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlNotLike(String value) {
            addCriterion("tsdl not like", value, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlIn(List<String> values) {
            addCriterion("tsdl in", values, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlNotIn(List<String> values) {
            addCriterion("tsdl not in", values, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlBetween(String value1, String value2) {
            addCriterion("tsdl between", value1, value2, "tsdl");
            return (Criteria) this;
        }

        public Criteria andTsdlNotBetween(String value1, String value2) {
            addCriterion("tsdl not between", value1, value2, "tsdl");
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