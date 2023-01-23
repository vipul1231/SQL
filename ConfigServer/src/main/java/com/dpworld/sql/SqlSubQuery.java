package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;
import com.dpworld.sql.table.ColumnNames;

import java.util.List;

public class SqlSubQuery implements SqlAliasElement {

    private SqlBuilder sqlBuilder;
    private CharSequence sql;
    private SqlAlias alias;
    private ColumnNames names;

    private SqlSubQuery(SqlBuilder sqlBuilder, SqlAlias alias) {
        this.sqlBuilder = sqlBuilder;
        this.alias = alias;
    }

    private SqlSubQuery(CharSequence sql, SqlAlias alias, ColumnNames names) {
        this.sql = sql;
        this.alias = alias;
        this.names = names;
    }

    public static SqlSubQuery of(SqlBuilder sql, CharSequence alias) {
        return new SqlSubQuery(sql, SqlAlias.of(alias));
    }

    public static SqlSubQuery of(CharSequence sql, CharSequence alias, ColumnNames names) {
        return new SqlSubQuery(sql, SqlAlias.of(alias), names);
    }

    public ColumnNames names() {
        if (sqlBuilder != null) {
            return sqlBuilder.select().names();
        }
        return names == null ? ColumnNames.of() : names;
    }

    @Override
    public SqlAlias alias() {
        return alias;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append('(');
        if (sqlBuilder != null) {
            sqlBuilder.render(sb, params);
        } else if (sql != null) {
            sb.append(sql);
        }
        sb.append(") ");
        alias.render(sb, params);
    }

}
