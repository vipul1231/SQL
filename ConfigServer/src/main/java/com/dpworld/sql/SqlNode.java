package com.dpworld.sql;

import com.dpworld.sql.element.SqlAliasElement;
import com.dpworld.sql.element.SqlElement;

import java.util.Objects;
import java.util.function.Function;

public abstract class SqlNode implements SqlElement {

    protected SqlBuilder builder;

    protected SqlNode(SqlBuilder builder) {
        this.builder = builder;
    }

    public SqlSelect select() {
        return withBuilder(SqlBuilder::select);
    }

    public SqlFrom from() {
        return withBuilder(SqlBuilder::from);
    }

    public SqlJoin join(SqlAliasElement table) {
        return withBuilder(b -> b.from().join(table));
    }

    public SqlJoin join(CharSequence table, CharSequence alias) {
        return join(SqlTable.of(table, alias));
    }

    public SqlJoin join(SqlBuilder table, CharSequence alias) {
        return join(SqlSubQuery.of(table, alias));
    }

    public SqlJoin leftJoin(SqlAliasElement table) {
        return withBuilder(b -> b.from().leftJoin(table));
    }

    public SqlJoin leftJoin(CharSequence table, CharSequence alias) {
        return leftJoin(SqlTable.of(table, alias));
    }

    public SqlJoin leftJoin(SqlBuilder table, CharSequence alias) {
        return leftJoin(SqlSubQuery.of(table, alias));
    }

    public SqlJoin fullJoin(SqlAliasElement table) {
        return withBuilder(b -> b.from().fullJoin(table));
    }

    public SqlJoin fullJoin(CharSequence table, CharSequence alias) {
        return fullJoin(SqlTable.of(table, alias));
    }

    public SqlJoin fullJoin(SqlBuilder table, CharSequence alias) {
        return fullJoin(SqlSubQuery.of(table, alias));
    }

    public SqlFrom crossJoin(SqlAliasElement table) {
        return withBuilder(b -> b.from().crossJoin(table));
    }

    public SqlFrom crossJoin(CharSequence table, CharSequence alias) {
        return crossJoin(SqlTable.of(table, alias));
    }

    public SqlWhere where() {
        return withBuilder(SqlBuilder::where);
    }

    public SqlCondition where(CharSequence column) {
        return withBuilder(b -> b.where(column));
    }

    public SqlCondition where(CharSequence alias, CharSequence column) {
        return withBuilder(b -> b.where(alias, column));
    }

    public SqlWhere noRows() {
        return withBuilder(b -> b.where().noRows());
    }

    public SqlOrderBy orderBy() {
        return withBuilder(SqlBuilder::orderBy);
    }

    public SqlGroupBy groupBy() {
        return withBuilder(SqlBuilder::groupBy);
    }

    public SqlGroupBy groupBy(CharSequence column) {
        return withBuilder(b -> b.groupBy(column));
    }

    public SqlGroupBy groupBy(CharSequence alias, CharSequence column) {
        return withBuilder(b -> b.groupBy(alias, column));
    }

    public SqlBuilder limit(int limit) {
        return withBuilder(b -> b.limit(limit));
    }

    public SqlBuilder noLimit() {
        return withBuilder(SqlBuilder::noLimit);
    }

    public SqlBuilder builder() {
        return withBuilder(b -> b);
    }

    private <X> X withBuilder(Function<SqlBuilder, X> function) {
        Objects.requireNonNull(builder, "SqlBuilder is null!");
        return function.apply(builder);
    }

    public abstract boolean isEmpty();

    public boolean isNotEmpty() {
        return !isEmpty();
    }

}
