package com.dpworld.sql;

import com.dpworld.sql.element.SqlElement;

import java.util.List;

public class SqlDelete implements SqlElement {

    private SqlTable table;
    private SqlWhere where;

    private SqlDelete(SqlTable table) {
        this.table = table;
    }

    public static SqlDelete from(CharSequence table) {
        return new SqlDelete(SqlTable.of(table, "t"));
    }

    public static SqlDelete from(CharSequence table, CharSequence alias) {
        return new SqlDelete(SqlTable.of(table, alias));
    }

    public SqlWhere where() {
        if (where == null) {
            where = new SqlWhere(table.alias(), null);
        }
        return where;
    }

    public SqlCondition where(CharSequence column) {
        return where().and(column);
    }

    public SqlCondition where(CharSequence alias, CharSequence column) {
        return where().and(alias, column);
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append("delete from ");
        table.render(sb, params);
        if (where != null && where.isNotEmpty()) {
            sb.append(" where ");
            where.render(sb, params);
        }
    }

}
