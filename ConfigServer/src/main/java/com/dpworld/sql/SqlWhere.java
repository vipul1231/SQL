package com.dpworld.sql;

import com.dpworld.sql.element.*;

import java.util.List;
import java.util.function.Consumer;

public class SqlWhere extends SqlNode {

    private static final SqlElement NOT = SqlDelimiter.of("not ");

    private SqlLeft left;
    private SqlAlias leftAlias;
    private SqlAlias rightAlias;
    private boolean noRows = false;
    private boolean not = false;
    private SqlElements<SqlElement> elements;

    protected SqlWhere(SqlAlias leftAlias, SqlAlias rightAlias) {
        this(null, leftAlias, rightAlias);
    }

    protected SqlWhere(SqlBuilder builder, SqlAlias leftAlias) {
        this(builder, leftAlias, leftAlias);
    }

    protected SqlWhere(SqlBuilder builder, SqlAlias leftAlias, SqlAlias rightAlias) {
        super(builder);
        this.leftAlias = leftAlias;
        this.rightAlias = rightAlias;

        elements = new SqlElements<>();
        left = new SqlLeft(this);
    }

    public SqlLeft and() {
        return andOr(SqlDelimiter.AND);
    }

    public SqlLeft or() {
        return andOr(SqlDelimiter.OR);
    }

    private SqlLeft andOr(SqlDelimiter delimiter) {
        if (!elements.isEmpty()) {
            elements.add(delimiter);
        }
        if (not) {
            elements.add(NOT);
            not = false;
        }
        return left;
    }

    protected SqlWhere add(SqlElement element) {
        elements.add(element);
        return this;
    }

    public SqlWhere not() {
        not = true;
        return this;
    }

    public SqlWhere and(SqlWhere where) {
        if (where.isNotEmpty()) {
            and();
            elements.add(SqlWrapper.parens(where));
        }
        return this;
    }

    public SqlWhere and(Consumer<SqlWhere> consumer) {
        var where = new SqlWhere(leftAlias, rightAlias);
        consumer.accept(where);
        and(where);
        return this;
    }

    public SqlCondition and(CharSequence column) {
        return and().column(column);
    }

    public SqlCondition and(CharSequence alias, CharSequence column) {
        return and().column(alias, column);
    }

    public SqlCondition andExpression(CharSequence expression) {
        return and().expression(expression);
    }

    public SqlWhere or(SqlWhere where) {
        if (where.isNotEmpty()) {
            or();
            elements.add(SqlWrapper.parens(where));
        }
        return this;
    }

    public SqlWhere or(Consumer<SqlWhere> consumer) {
        var where = new SqlWhere(leftAlias, rightAlias);
        consumer.accept(where);
        or(where);
        return this;
    }

    public SqlCondition or(CharSequence column) {
        return or().column(column);
    }

    public SqlCondition or(CharSequence alias, CharSequence column) {
        return or().column(alias, column);
    }

    public SqlCondition orExpression(String expression) {
        return or().expression(expression);
    }

    @Override
    public boolean isEmpty() {
        return !noRows && elements.isEmpty();
    }

    @Override
    public SqlWhere noRows() {
        noRows = true;
        return this;
    }

    public boolean isNoRows() {
        return noRows;
    }

    protected SqlWhere rightAlias(SqlAlias rightAlias) {
        this.rightAlias = rightAlias;
        return this;
    }

    protected SqlAlias rightAlias() {
        return rightAlias;
    }

    protected SqlAlias leftAlias() {
        return leftAlias;
    }

    public SqlWhere exists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return and().exists(table, consumer);
    }

    public SqlWhere exists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        return and().exists(table, alias, consumer);
    }

    public SqlWhere notExists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return and().notExists(table, consumer);
    }

    public SqlWhere notExists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        return and().notExists(table, alias, consumer);
    }

    public SqlWhere orExists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return or().exists(table, consumer);
    }

    public SqlWhere orExists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        return or().exists(table, alias, consumer);
    }

    public SqlWhere orNotExists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return or().notExists(table, consumer);
    }

    public SqlWhere orNotExists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        return or().notExists(table, alias, consumer);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        if (noRows) {
            sb.append("1 = 2");
        } else {
            elements.render(sb, params);
        }
    }

}
