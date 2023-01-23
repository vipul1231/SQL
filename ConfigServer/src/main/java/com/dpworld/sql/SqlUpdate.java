package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;
import com.dpworld.sql.element.SqlElement;

import java.util.List;
import java.util.Objects;

public class SqlUpdate implements SqlElement {

    private SqlTable table;
    private SqlAlias fromAlias;
    private SqlFrom from;
    private SqlSet set;
    private SqlWhere where;

    private SqlUpdate(CharSequence table) {
        this.table = SqlTable.of(table, "t");

        fromAlias = SqlAlias.of("s");
        set = new SqlSet(this, fromAlias);
        where = new SqlWhere(this.table.alias(), fromAlias);
    }

    public static SqlUpdate table(CharSequence table) {
        return new SqlUpdate(table);
    }

    public SqlFrom from() {
        Objects.requireNonNull(from, "SqlFrom is null!");
        return from;
    }

    public SqlUpdate from(SqlAliasElement table) {
        from = new SqlFrom(null, table);
        return this;
    }

    public SqlUpdate from(CharSequence from) {
        return from(SqlTable.of(from, fromAlias));
    }

    public SqlUpdate from(SqlBuilder from) {
        return from(SqlSubQuery.of(from, fromAlias));
    }

    public SqlSet.SetColumn set(CharSequence column) {
        return set.column(column);
    }

    public SqlUpdate set(Iterable<? extends CharSequence> columns) {
        columns.forEach(column -> set.column(column).column(column));
        return this;
    }

    public SqlWhere where() {
        return where;
    }

    public SqlCondition where(CharSequence column) {
        return where().and(column);
    }

    public SqlCondition where(CharSequence alias, CharSequence column) {
        return where().and(alias, column);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean isNotEmpty() {
        return set.isNotEmpty();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append("update ");
        table.render(sb, params);
        sb.append(" set ");
        set.render(sb, params);
        if (from != null) {
            sb.append(" from ");
            from.render(sb, params);
        }
        if (where.isNotEmpty()) {
            sb.append(" where ");
            where.render(sb, params);
        }
    }

}
