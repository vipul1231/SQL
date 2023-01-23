package com.dpworld.sql;

import com.dpworld.sql.element.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SqlCondition {

    private static final SqlElement EQUALS = SqlDelimiter.of(" = ");
    private static final SqlElement NOT_EQUALS = SqlDelimiter.of(" <> ");
    private static final SqlElement LESS_THAN = SqlDelimiter.of(" < ");
    private static final SqlElement GREATER_THAN = SqlDelimiter.of(" > ");
    private static final SqlElement LESS_THAN_EQUAL = SqlDelimiter.of(" <= ");
    private static final SqlElement GREATER_THAN_EQUAL = SqlDelimiter.of(" >= ");

    private static final SqlElement IS_NULL = SqlDelimiter.of(" is null");
    private static final SqlElement IS_NOT_NULL = SqlDelimiter.of(" is not null");
    private static final SqlElement NOT = SqlDelimiter.of(" not");

    private SqlWhere where;
    private SqlRight right;

    protected SqlCondition(SqlWhere where) {
        this.where = where;
        right = new SqlRight(where);
    }

    public SqlWhere isNull() {
        where.add(IS_NULL);
        return where;
    }

    public SqlWhere isNotNull() {
        where.add(IS_NOT_NULL);
        return where;
    }

    public SqlRight eq() {
        where.add(EQUALS);
        return right;
    }

    public SqlWhere eq(CharSequence column) {
        return eq().column(column);
    }

    public SqlWhere eq(CharSequence alias, CharSequence column) {
        return eq().column(alias, column);
    }

    public SqlWhere eqLiteral(Object value) {
        return eq().literal(value);
    }

    public SqlWhere eqExpression(CharSequence value) {
        return eq().expression(value);
    }

    public SqlWhere eqJsonInt(CharSequence column, CharSequence property) {
        return eq().expression(SqlJson.asInt(column, property));
    }

    public SqlWhere eq(Object value) {
        return eq().literal(value);
    }

    public SqlRight ne() {
        where.add(NOT_EQUALS);
        return right;
    }

    public SqlWhere ne(CharSequence column) {
        return ne().column(column);
    }

    public SqlWhere ne(CharSequence alias, CharSequence column) {
        return ne().column(alias, column);
    }

    public SqlWhere neLiteral(CharSequence value) {
        return ne().literal(value);
    }

    public SqlWhere neExpression(CharSequence value) {
        return ne().expression(value);
    }

    public SqlWhere ne(Object value) {
        return ne().literal(value);
    }

    public SqlRight lt() {
        where.add(LESS_THAN);
        return right;
    }

    public SqlWhere lt(CharSequence column) {
        return lt().column(column);
    }

    public SqlWhere lt(CharSequence alias, CharSequence column) {
        return lt().column(alias, column);
    }

    public SqlWhere ltLiteral(CharSequence value) {
        return lt().literal(value);
    }

    public SqlWhere ltExpression(CharSequence value) {
        return lt().expression(value);
    }

    public SqlWhere lt(Object value) {
        return lt().literal(value);
    }

    public SqlRight gt() {
        where.add(GREATER_THAN);
        return right;
    }

    public SqlWhere gt(CharSequence column) {
        return gt().column(column);
    }

    public SqlWhere gt(CharSequence alias, CharSequence column) {
        return gt().column(alias, column);
    }

    public SqlWhere gtLiteral(CharSequence value) {
        return gt().literal(value);
    }

    public SqlWhere gtExpression(CharSequence value) {
        return gt().expression(value);
    }

    public SqlWhere gt(Object value) {
        return gt().literal(value);
    }

    public SqlRight lte() {
        where.add(LESS_THAN_EQUAL);
        return right;
    }

    public SqlWhere lte(CharSequence column) {
        return lte().column(column);
    }

    public SqlWhere lte(CharSequence alias, CharSequence column) {
        return lte().column(alias, column);
    }

    public SqlWhere lteLiteral(CharSequence value) {
        return lte().literal(value);
    }

    public SqlWhere lteExpression(CharSequence value) {
        return lte().expression(value);
    }

    public SqlWhere lte(Object value) {
        return lte().literal(value);
    }

    public SqlRight gte() {
        where.add(GREATER_THAN_EQUAL);
        return right;
    }

    public SqlWhere gte(CharSequence column) {
        return gte().column(column);
    }

    public SqlWhere gte(CharSequence alias, CharSequence column) {
        return gte().column(alias, column);
    }

    public SqlWhere gteLiteral(CharSequence value) {
        return gte().literal(value);
    }

    public SqlWhere gteExpression(CharSequence value) {
        return gte().expression(value);
    }

    public SqlWhere gte(Object value) {
        return gte().literal(value);
    }

    public SqlWhere in(Collection<?> items) {
        return where.add(SqlIn.of(items));
    }

    public SqlWhere notIn(Collection<?> items) {
        where.add(NOT);
        return in(items);
    }

    public SqlWhere notIn(Object[] items) {
        where.add(NOT);
        return in(items);
    }

    public SqlWhere in(Object[] items) {
        if (items != null && items.length == 1) {
            return eq(items[0]);
        }

        List<Object> list = new ArrayList<>();
        if (items != null && items.length > 0) {
            for (Object item : items) {
                if (item != null) {
                    list.add(item);
                }
            }
        }
        return in(list);
    }

    public SqlWhere between(Object min, Object max) {
        return where.add(SqlBetween.of(min, max));
    }

    public SqlWhere startsWith(String value) {
        return where.add(SqlLike.of(value, false, true));
    }

    public SqlWhere notStartsWith(String value) {
        return where.add(NOT).add(SqlLike.of(value, false, true));
    }

    public SqlWhere endsWith(String value) {
        return where.add(SqlLike.of(value, true, false));
    }

    public SqlWhere notEndsWith(String value) {
        return where.add(NOT).add(SqlLike.of(value, true, false));
    }

    public SqlWhere contains(String value) {
        return where.add(SqlLike.of(value, true, true));
    }

    public SqlWhere notContains(String value) {
        return where.add(NOT).add(SqlLike.of(value, true, true));
    }

}
