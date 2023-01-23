package com.dpworld.sql;

import com.dpworld.sql.element.*;

import java.util.function.Consumer;

public class SqlLeft {

    private static final SqlElement EXISTS = SqlDelimiter.of("exists ");
    private static final SqlElement NOT_EXISTS = SqlDelimiter.of("not exists ");

    private SqlWhere where;
    private SqlCondition condition;

    protected SqlLeft(SqlWhere where) {
        this.where = where;
        condition = new SqlCondition(where);
    }

    public SqlCondition column(CharSequence column) {
        return column(where.leftAlias(), column);
    }

    public SqlCondition column(CharSequence alias, CharSequence column) {
        where.add(SqlColumn.of(alias, column));
        return condition();
    }

    public SqlCondition expression(CharSequence expression) {
        where.add(SqlExpression.of(expression));
        return condition();
    }

    private SqlCondition condition() {
        return condition;
    }

    public SqlWhere exists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return exists(table, "x", consumer);
    }

    public SqlWhere exists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        SqlBuilder sql = SqlBuilder.from(table, alias)
                .select()
                .expression("1")
                .where()
                .rightAlias(where.leftAlias())
                .builder();
        consumer.accept(sql);

        where.add(EXISTS).add(SqlWrapper.parens(sql));
        return where;
    }

    public SqlWhere notExists(CharSequence table, Consumer<SqlBuilder> consumer) {
        return notExists(table, "x", consumer);
    }

    public SqlWhere notExists(CharSequence table, CharSequence alias, Consumer<SqlBuilder> consumer) {
        SqlBuilder sql = SqlBuilder.from(table, alias)
                .select()
                .expression("1")
                .where()
                .rightAlias(where.leftAlias())
                .builder();
        consumer.accept(sql);

        where.add(NOT_EXISTS).add(SqlWrapper.parens(sql));
        return where;
    }

    public SqlAlias alias() {
        return where.leftAlias();
    }

}
