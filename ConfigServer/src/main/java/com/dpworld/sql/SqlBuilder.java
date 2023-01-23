package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;
import com.dpworld.sql.element.SqlElement;
import org.springframework.batch.support.DatabaseType;

import java.util.List;
import java.util.Objects;

public class SqlBuilder implements SqlElement {

    private DatabaseType type;
    private SqlSelect select;
    private SqlFrom from;
    private SqlWhere where;
    private SqlOrderBy order;
    private SqlGroupBy group;
    private Integer limit;

    private SqlBuilder() {
    }

    private SqlBuilder(SqlAliasElement table) {
        from = new SqlFrom(this, table);
    }

    public SqlSelect select() {
        if (select == null) {
            if (from != null) {
                select = new SqlSelect(this, from.alias());
            } else {
                select = new SqlSelect(this, SqlAlias.of("s"));
            }
        }
        return select;
    }

    public SqlFrom from() {
        Objects.requireNonNull(from, "SqlFrom is null!");
        return from;
    }

    public static SqlBuilder noFrom() {
        return new SqlBuilder();
    }

    public static SqlBuilder from(SqlAliasElement table) {
        return new SqlBuilder(table);
    }

    public static SqlBuilder from(CharSequence table, CharSequence alias) {
        return new SqlBuilder(SqlTable.of(table, alias));
    }

    public static SqlBuilder from(SqlBuilder sql, CharSequence alias) {
        return new SqlBuilder(SqlSubQuery.of(sql, alias));
    }

    public SqlJoin join(CharSequence table, CharSequence alias) {
        return from().join(table, alias);
    }

    public SqlJoin leftJoin(CharSequence table, CharSequence alias) {
        return from().leftJoin(table, alias);
    }

    public SqlJoin fullJoin(CharSequence table, CharSequence alias) {
        return from().fullJoin(table, alias);
    }

    public SqlFrom crossJoin(CharSequence table, CharSequence alias) {
        return from().crossJoin(table, alias);
    }

    public SqlWhere where() {
        if (where == null) {
            where = new SqlWhere(this, from().alias());
        }
        return where;
    }

    public SqlCondition where(CharSequence column) {
        return where().and(column);
    }

    public SqlCondition where(CharSequence alias, CharSequence column) {
        return where().and(alias, column);
    }

    public SqlWhere where(SqlWhere where) {
        if (this.where == null) {
            this.where = where;
        } else {
            this.where.and(where);
        }
        return this.where;
    }

    public SqlWhere where(Iterable<? extends CharSequence> columns) {
        columns.forEach(column -> where(column).eq(column));
        return where;
    }

    public SqlOrderBy orderBy() {
        if (order == null) {
            order = new SqlOrderBy(this, from().alias());
        }
        return order;
    }

    public SqlGroupBy groupBy() {
        if (group == null) {
            group = new SqlGroupBy(this, from().alias());
        }
        return group;
    }

    public SqlGroupBy groupBy(CharSequence column) {
        return groupBy().and(column);
    }

    public SqlGroupBy groupBy(CharSequence alias, CharSequence column) {
        return groupBy().and(alias, column);
    }

    public SqlGroupBy groupBy(SqlColumn column) {
        return groupBy().and(column);
    }

    public SqlBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    public SqlBuilder noLimit() {
        limit = null;
        return this;
    }

    public SqlBuilder type(DatabaseType type) {
        this.type = type;
        return this;
    }

    private DatabaseType getType() {
//        if (type == null) {
//            if (ContextUtils.isLoaded()) {
//                type = ContextUtils.getBean("defaultJdbcTemplate", CustomJdbcTemplate.class).getDatabaseType();
//            } else {
//                type = DatabaseType.POSTGRES;
//            }
//        }
        return type;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        renderSelect(sb, params);
        renderFrom(sb, params);
        renderWhere(sb, params);
        renderGroupBy(sb, params);
        renderOrderBy(sb, params);

        if (limit != null && getType() != DatabaseType.ORACLE) {
            sb.append(" limit ").append(limit);
        }
    }

    private void renderSelect(StringBuilder sb, List<Object> params) {
        sb.append("select ");
        if (limit != null && getType() == DatabaseType.SQLSERVER) {
            sb.append("top ").append(limit).append(' ');
        }
        if (select == null || select.isEmpty()) {
            sb.append("*");
        } else {
            select.render(sb, params);
        }
    }

    private void renderFrom(StringBuilder sb, List<Object> params) {
        if (from != null) {
            sb.append(" from ");
            from.render(sb, params);
        }
    }

    private void renderWhere(StringBuilder sb, List<Object> params) {
        if (limit != null && getType() == DatabaseType.ORACLE) {
            sb.append(" where rownum <= ").append(limit);

            if (where != null && where.isNotEmpty()) {
                sb.append(" and (");
                where.render(sb, params);
                sb.append(')');
            }
        } else {
            if (where != null && where.isNotEmpty()) {
                sb.append(" where ");
                where.render(sb, params);
            }
        }
    }

    private void renderGroupBy(StringBuilder sb, List<Object> params) {
        if (group != null && group.isNotEmpty()) {
            sb.append(" group by ");
            group.render(sb, params);
        }
    }

    private void renderOrderBy(StringBuilder sb, List<Object> params) {
        if (order != null && order.isNotEmpty()) {
            sb.append(" order by ");
            order.render(sb, params);
        }
    }

}
