package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SqlFrom extends SqlNode {

    private SqlAliasElement table;
    private List<SqlJoin> joins;

    protected SqlFrom(SqlBuilder builder, SqlAliasElement table) {
        super(builder);
        this.table = table;
    }

    public SqlAlias alias() {
        return table.alias();
    }

    public SqlAliasElement table() {
        return table;
    }

    @Override
    public SqlJoin join(SqlAliasElement table) {
        var result = new SqlJoin(this, table);
        if (joins == null) {
            joins = new ArrayList<>();
        }
        joins.add(result);
        return result;
    }

    @Override
    public SqlJoin leftJoin(SqlAliasElement table) {
        return join(table).type("left join");
    }

    @Override
    public SqlJoin fullJoin(SqlAliasElement table) {
        return join(table).type("full join");
    }

    @Override
    public SqlFrom crossJoin(SqlAliasElement table) {
        join(table).type("cross join");
        return this;
    }

    public boolean hasJoin(CharSequence table, CharSequence alias) {
        return findJoin(table, alias) != null;
    }

    public SqlJoin findJoin(CharSequence table, CharSequence alias) {
        if (joins != null) {
            var sqlTable = SqlTable.of(table, alias);
            for (SqlJoin join : joins) {
                if (join.table().equals(sqlTable)) {
                    return join;
                }
            }
        }
        return null;
    }

    public SqlJoin findJoin(CharSequence alias) {
        if (joins != null) {
            for (SqlJoin join : joins) {
                if (join.alias().equals(alias)) {
                    return join;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int getJoinCount() {
        return CollectionUtils.isEmpty(joins) ? 0 : joins.size();
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        table.render(sb, params);
        if (joins != null && !joins.isEmpty()) {
            for (SqlJoin join : joins) {
                sb.append(' ');
                join.render(sb, params);
            }
        }
    }

}
