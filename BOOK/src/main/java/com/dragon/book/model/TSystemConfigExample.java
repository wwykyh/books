package com.dragon.book.model;

import java.util.ArrayList;
import java.util.List;

public class TSystemConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSystemConfigExample() {
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

        public Criteria andPenTimeIsNull() {
            addCriterion("pen_time is null");
            return (Criteria) this;
        }

        public Criteria andPenTimeIsNotNull() {
            addCriterion("pen_time is not null");
            return (Criteria) this;
        }

        public Criteria andPenTimeEqualTo(Integer value) {
            addCriterion("pen_time =", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeNotEqualTo(Integer value) {
            addCriterion("pen_time <>", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeGreaterThan(Integer value) {
            addCriterion("pen_time >", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pen_time >=", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeLessThan(Integer value) {
            addCriterion("pen_time <", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeLessThanOrEqualTo(Integer value) {
            addCriterion("pen_time <=", value, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeIn(List<Integer> values) {
            addCriterion("pen_time in", values, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeNotIn(List<Integer> values) {
            addCriterion("pen_time not in", values, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeBetween(Integer value1, Integer value2) {
            addCriterion("pen_time between", value1, value2, "penTime");
            return (Criteria) this;
        }

        public Criteria andPenTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("pen_time not between", value1, value2, "penTime");
            return (Criteria) this;
        }

        public Criteria andBookNumIsNull() {
            addCriterion("book_num is null");
            return (Criteria) this;
        }

        public Criteria andBookNumIsNotNull() {
            addCriterion("book_num is not null");
            return (Criteria) this;
        }

        public Criteria andBookNumEqualTo(Integer value) {
            addCriterion("book_num =", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotEqualTo(Integer value) {
            addCriterion("book_num <>", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumGreaterThan(Integer value) {
            addCriterion("book_num >", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_num >=", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumLessThan(Integer value) {
            addCriterion("book_num <", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumLessThanOrEqualTo(Integer value) {
            addCriterion("book_num <=", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumIn(List<Integer> values) {
            addCriterion("book_num in", values, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotIn(List<Integer> values) {
            addCriterion("book_num not in", values, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumBetween(Integer value1, Integer value2) {
            addCriterion("book_num between", value1, value2, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotBetween(Integer value1, Integer value2) {
            addCriterion("book_num not between", value1, value2, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookTimeIsNull() {
            addCriterion("book_time is null");
            return (Criteria) this;
        }

        public Criteria andBookTimeIsNotNull() {
            addCriterion("book_time is not null");
            return (Criteria) this;
        }

        public Criteria andBookTimeEqualTo(Integer value) {
            addCriterion("book_time =", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotEqualTo(Integer value) {
            addCriterion("book_time <>", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeGreaterThan(Integer value) {
            addCriterion("book_time >", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_time >=", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeLessThan(Integer value) {
            addCriterion("book_time <", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeLessThanOrEqualTo(Integer value) {
            addCriterion("book_time <=", value, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeIn(List<Integer> values) {
            addCriterion("book_time in", values, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotIn(List<Integer> values) {
            addCriterion("book_time not in", values, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeBetween(Integer value1, Integer value2) {
            addCriterion("book_time between", value1, value2, "bookTime");
            return (Criteria) this;
        }

        public Criteria andBookTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("book_time not between", value1, value2, "bookTime");
            return (Criteria) this;
        }

        public Criteria andInfractionsIsNull() {
            addCriterion("infractions is null");
            return (Criteria) this;
        }

        public Criteria andInfractionsIsNotNull() {
            addCriterion("infractions is not null");
            return (Criteria) this;
        }

        public Criteria andInfractionsEqualTo(Integer value) {
            addCriterion("infractions =", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsNotEqualTo(Integer value) {
            addCriterion("infractions <>", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsGreaterThan(Integer value) {
            addCriterion("infractions >", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("infractions >=", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsLessThan(Integer value) {
            addCriterion("infractions <", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsLessThanOrEqualTo(Integer value) {
            addCriterion("infractions <=", value, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsIn(List<Integer> values) {
            addCriterion("infractions in", values, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsNotIn(List<Integer> values) {
            addCriterion("infractions not in", values, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsBetween(Integer value1, Integer value2) {
            addCriterion("infractions between", value1, value2, "infractions");
            return (Criteria) this;
        }

        public Criteria andInfractionsNotBetween(Integer value1, Integer value2) {
            addCriterion("infractions not between", value1, value2, "infractions");
            return (Criteria) this;
        }

        public Criteria andAdminBooksIsNull() {
            addCriterion("admin_books is null");
            return (Criteria) this;
        }

        public Criteria andAdminBooksIsNotNull() {
            addCriterion("admin_books is not null");
            return (Criteria) this;
        }

        public Criteria andAdminBooksEqualTo(Integer value) {
            addCriterion("admin_books =", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksNotEqualTo(Integer value) {
            addCriterion("admin_books <>", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksGreaterThan(Integer value) {
            addCriterion("admin_books >", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_books >=", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksLessThan(Integer value) {
            addCriterion("admin_books <", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksLessThanOrEqualTo(Integer value) {
            addCriterion("admin_books <=", value, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksIn(List<Integer> values) {
            addCriterion("admin_books in", values, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksNotIn(List<Integer> values) {
            addCriterion("admin_books not in", values, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksBetween(Integer value1, Integer value2) {
            addCriterion("admin_books between", value1, value2, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminBooksNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_books not between", value1, value2, "adminBooks");
            return (Criteria) this;
        }

        public Criteria andAdminTimeIsNull() {
            addCriterion("admin_time is null");
            return (Criteria) this;
        }

        public Criteria andAdminTimeIsNotNull() {
            addCriterion("admin_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdminTimeEqualTo(Integer value) {
            addCriterion("admin_time =", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotEqualTo(Integer value) {
            addCriterion("admin_time <>", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeGreaterThan(Integer value) {
            addCriterion("admin_time >", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_time >=", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeLessThan(Integer value) {
            addCriterion("admin_time <", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeLessThanOrEqualTo(Integer value) {
            addCriterion("admin_time <=", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeIn(List<Integer> values) {
            addCriterion("admin_time in", values, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotIn(List<Integer> values) {
            addCriterion("admin_time not in", values, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeBetween(Integer value1, Integer value2) {
            addCriterion("admin_time between", value1, value2, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_time not between", value1, value2, "adminTime");
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