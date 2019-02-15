package com.dragon.book.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andJyrqEqualTo(Date value) {
            addCriterionForJDBCDate("jyrq =", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("jyrq <>", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqGreaterThan(Date value) {
            addCriterionForJDBCDate("jyrq >", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jyrq >=", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqLessThan(Date value) {
            addCriterionForJDBCDate("jyrq <", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jyrq <=", value, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqIn(List<Date> values) {
            addCriterionForJDBCDate("jyrq in", values, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("jyrq not in", values, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jyrq between", value1, value2, "jyrq");
            return (Criteria) this;
        }

        public Criteria andJyrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jyrq not between", value1, value2, "jyrq");
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

        public Criteria andJhghrqEqualTo(Date value) {
            addCriterionForJDBCDate("jhghrq =", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("jhghrq <>", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqGreaterThan(Date value) {
            addCriterionForJDBCDate("jhghrq >", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jhghrq >=", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqLessThan(Date value) {
            addCriterionForJDBCDate("jhghrq <", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("jhghrq <=", value, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqIn(List<Date> values) {
            addCriterionForJDBCDate("jhghrq in", values, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("jhghrq not in", values, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jhghrq between", value1, value2, "jhghrq");
            return (Criteria) this;
        }

        public Criteria andJhghrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("jhghrq not between", value1, value2, "jhghrq");
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

        public Criteria andXjrqEqualTo(Date value) {
            addCriterionForJDBCDate("xjrq =", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("xjrq <>", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqGreaterThan(Date value) {
            addCriterionForJDBCDate("xjrq >", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("xjrq >=", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqLessThan(Date value) {
            addCriterionForJDBCDate("xjrq <", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("xjrq <=", value, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqIn(List<Date> values) {
            addCriterionForJDBCDate("xjrq in", values, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("xjrq not in", values, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("xjrq between", value1, value2, "xjrq");
            return (Criteria) this;
        }

        public Criteria andXjrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("xjrq not between", value1, value2, "xjrq");
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

        public Criteria andGhrqEqualTo(Date value) {
            addCriterionForJDBCDate("ghrq =", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("ghrq <>", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqGreaterThan(Date value) {
            addCriterionForJDBCDate("ghrq >", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ghrq >=", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqLessThan(Date value) {
            addCriterionForJDBCDate("ghrq <", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ghrq <=", value, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqIn(List<Date> values) {
            addCriterionForJDBCDate("ghrq in", values, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("ghrq not in", values, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ghrq between", value1, value2, "ghrq");
            return (Criteria) this;
        }

        public Criteria andGhrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ghrq not between", value1, value2, "ghrq");
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