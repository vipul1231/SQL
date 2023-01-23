package com.dpworld.sql;

import com.dpworld.sql.element.SqlElement;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SqlInsert implements SqlElement {

    private SqlTable table;
    private SqlBuilder sql;
    private boolean ignoreConflicts = false;

    private SqlInsert(CharSequence table) {
        this.table = SqlTable.of(table, null);
    }

    public static SqlInsert into(CharSequence table) {
        return new SqlInsert(table);
    }

    public SqlTable table() {
        return table;
    }

    public SqlBuilder from(SqlBuilder sql, CharSequence alias) {
        this.sql = SqlBuilder.from(sql, alias);
        return this.sql;
    }

    public SqlBuilder from(SqlBuilder sql) {
        this.sql = sql;
        return this.sql;
    }

    public SqlBuilder from(CharSequence table, CharSequence alias) {
        sql = SqlBuilder.from(table, alias);
        return sql;
    }

    public SqlInsert sql(SqlBuilder sql) {
        this.sql = sql;
        return this;
    }

    public SqlWhere where() {
        return sql.where();
    }

    public SqlInsert ignoreConflicts() {
        ignoreConflicts = true;
        return this;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append("insert into ");
        table.render(sb, params);

        if (sql != null) {
            sb.append(" (").append(StringUtils.join(sql.select().names(), ", ")).append(") ");
            sql.render(sb, params);
        }

        if (ignoreConflicts) {
            sb.append("  on conflict do nothing");
        }
    }

}
