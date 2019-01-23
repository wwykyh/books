package com.dragon.book.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(String value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(String value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(String value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(String value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(String value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(String value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLike(String value) {
            addCriterion("book_id like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotLike(String value) {
            addCriterion("book_id not like", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<String> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<String> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(String value1, String value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(String value1, String value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
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

        public Criteria andCbrqEqualTo(Date value) {
            addCriterionForJDBCDate("cbrq =", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("cbrq <>", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqGreaterThan(Date value) {
            addCriterionForJDBCDate("cbrq >", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cbrq >=", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqLessThan(Date value) {
            addCriterionForJDBCDate("cbrq <", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("cbrq <=", value, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqIn(List<Date> values) {
            addCriterionForJDBCDate("cbrq in", values, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("cbrq not in", values, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cbrq between", value1, value2, "cbrq");
            return (Criteria) this;
        }

        public Criteria andCbrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("cbrq not between", value1, value2, "cbrq");
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

        public Criteria andRkrqIsNull() {
            addCriterion("rkrq is null");
            return (Criteria) this;
        }

        public Criteria andRkrqIsNotNull() {
            addCriterion("rkrq is not null");
            return (Criteria) this;
        }

        public Criteria andRkrqEqualTo(Date value) {
            addCriterionForJDBCDate("rkrq =", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("rkrq <>", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqGreaterThan(Date value) {
            addCriterionForJDBCDate("rkrq >", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rkrq >=", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqLessThan(Date value) {
            addCriterionForJDBCDate("rkrq <", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rkrq <=", value, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqIn(List<Date> values) {
            addCriterionForJDBCDate("rkrq in", values, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("rkrq not in", values, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rkrq between", value1, value2, "rkrq");
            return (Criteria) this;
        }

        public Criteria andRkrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rkrq not between", value1, value2, "rkrq");
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

        public Criteria andCsIsNull() {
            addCriterion("cs is null");
            return (Criteria) this;
        }

        public Criteria andCsIsNotNull() {
            addCriterion("cs is not null");
            return (Criteria) this;
        }

        public Criteria andCsEqualTo(Integer value) {
            addCriterion("cs =", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotEqualTo(Integer value) {
            addCriterion("cs <>", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThan(Integer value) {
            addCriterion("cs >", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsGreaterThanOrEqualTo(Integer value) {
            addCriterion("cs >=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThan(Integer value) {
            addCriterion("cs <", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsLessThanOrEqualTo(Integer value) {
            addCriterion("cs <=", value, "cs");
            return (Criteria) this;
        }

        public Criteria andCsIn(List<Integer> values) {
            addCriterion("cs in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotIn(List<Integer> values) {
            addCriterion("cs not in", values, "cs");
            return (Criteria) this;
        }

        public Criteria andCsBetween(Integer value1, Integer value2) {
            addCriterion("cs between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andCsNotBetween(Integer value1, Integer value2) {
            addCriterion("cs not between", value1, value2, "cs");
            return (Criteria) this;
        }

        public Criteria andKcIsNull() {
            addCriterion("kc is null");
            return (Criteria) this;
        }

        public Criteria andKcIsNotNull() {
            addCriterion("kc is not null");
            return (Criteria) this;
        }

        public Criteria andKcEqualTo(Integer value) {
            addCriterion("kc =", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcNotEqualTo(Integer value) {
            addCriterion("kc <>", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcGreaterThan(Integer value) {
            addCriterion("kc >", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcGreaterThanOrEqualTo(Integer value) {
            addCriterion("kc >=", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcLessThan(Integer value) {
            addCriterion("kc <", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcLessThanOrEqualTo(Integer value) {
            addCriterion("kc <=", value, "kc");
            return (Criteria) this;
        }

        public Criteria andKcIn(List<Integer> values) {
            addCriterion("kc in", values, "kc");
            return (Criteria) this;
        }

        public Criteria andKcNotIn(List<Integer> values) {
            addCriterion("kc not in", values, "kc");
            return (Criteria) this;
        }

        public Criteria andKcBetween(Integer value1, Integer value2) {
            addCriterion("kc between", value1, value2, "kc");
            return (Criteria) this;
        }

        public Criteria andKcNotBetween(Integer value1, Integer value2) {
            addCriterion("kc not between", value1, value2, "kc");
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

        public Criteria andTszlIsNull() {
            addCriterion("TSZL is null");
            return (Criteria) this;
        }

        public Criteria andTszlIsNotNull() {
            addCriterion("TSZL is not null");
            return (Criteria) this;
        }

        public Criteria andTszlEqualTo(String value) {
            addCriterion("TSZL =", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotEqualTo(String value) {
            addCriterion("TSZL <>", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlGreaterThan(String value) {
            addCriterion("TSZL >", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlGreaterThanOrEqualTo(String value) {
            addCriterion("TSZL >=", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLessThan(String value) {
            addCriterion("TSZL <", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLessThanOrEqualTo(String value) {
            addCriterion("TSZL <=", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlLike(String value) {
            addCriterion("TSZL like", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotLike(String value) {
            addCriterion("TSZL not like", value, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlIn(List<String> values) {
            addCriterion("TSZL in", values, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotIn(List<String> values) {
            addCriterion("TSZL not in", values, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlBetween(String value1, String value2) {
            addCriterion("TSZL between", value1, value2, "tszl");
            return (Criteria) this;
        }

        public Criteria andTszlNotBetween(String value1, String value2) {
            addCriterion("TSZL not between", value1, value2, "tszl");
            return (Criteria) this;
        }

        public Criteria andWzIsNull() {
            addCriterion("wz is null");
            return (Criteria) this;
        }

        public Criteria andWzIsNotNull() {
            addCriterion("wz is not null");
            return (Criteria) this;
        }

        public Criteria andWzEqualTo(String value) {
            addCriterion("wz =", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotEqualTo(String value) {
            addCriterion("wz <>", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzGreaterThan(String value) {
            addCriterion("wz >", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzGreaterThanOrEqualTo(String value) {
            addCriterion("wz >=", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLessThan(String value) {
            addCriterion("wz <", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLessThanOrEqualTo(String value) {
            addCriterion("wz <=", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzLike(String value) {
            addCriterion("wz like", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotLike(String value) {
            addCriterion("wz not like", value, "wz");
            return (Criteria) this;
        }

        public Criteria andWzIn(List<String> values) {
            addCriterion("wz in", values, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotIn(List<String> values) {
            addCriterion("wz not in", values, "wz");
            return (Criteria) this;
        }

        public Criteria andWzBetween(String value1, String value2) {
            addCriterion("wz between", value1, value2, "wz");
            return (Criteria) this;
        }

        public Criteria andWzNotBetween(String value1, String value2) {
            addCriterion("wz not between", value1, value2, "wz");
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