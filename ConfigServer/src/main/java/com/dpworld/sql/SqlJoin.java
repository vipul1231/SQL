package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;

import java.util.List;

public class SqlJoin extends SqlNode {

    private CharSequence type = "join";
    private SqlFrom from;
    private SqlAliasElement table;
    private SqlWhere where;

    protected SqlJoin(SqlFrom from, SqlAliasElement table) {
        super(from.builder);
        this.from = from;
        this.table = table;
    }

    SqlJoin type(String type) {
        this.type = type;
        return this;
    }

    public SqlWhere on() {
        if (where == null) {
            where = new SqlWhere(builder, from.alias(), table.alias());
        }
        return where;
    }

    public SqlWhere on(Iterable<? extends CharSequence> columns) {
        var where = on();
        for (CharSequence column : columns) {
            where.and(column).eq(table.alias(), column);
        }
        return where;
    }

    public SqlCondition on(CharSequence column) {
        return on().and(column);
    }

    public SqlCondition on(SqlColumn column) {
        return on().and(column.alias(), column.column());
    }

    public SqlCondition on(CharSequence alias, CharSequence column) {
        return on().and(alias, column);
    }

    public SqlCondition onExpression(CharSequence expression) {
        return on().andExpression(expression);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public SqlAlias alias() {
        return table.alias();
    }

    public SqlAliasElement table() {
        return table;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(type).append(' ');
        table.render(sb, params);

        if (where != null) {
            sb.append(" on ");
            where.render(sb, params);
        }
    }

}
