package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlColumns;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SqlGroupBy extends SqlNode {

    private SqlAlias alias;
    private SqlColumns columns;
    private CharSequence having;
    private List<Object> havingParams;

    protected SqlGroupBy(SqlBuilder builder, SqlAlias alias) {
        super(builder);
        this.alias = alias;
        columns = new SqlColumns();
    }

    public SqlGroupBy and(CharSequence column) {
        return and(alias, column);
    }

    public SqlGroupBy and(CharSequence alias, CharSequence column) {
        columns.column(alias, column);
        return this;
    }

    public SqlGroupBy and(SqlColumn column) {
        return and(column);
    }

    public SqlGroupBy and(Iterable<? extends CharSequence> columns) {
        columns.forEach(column -> and(column));
        return this;
    }

    public SqlGroupBy and(CharSequence alias, Iterable<? extends CharSequence> columns) {
        columns.forEach(column -> and(alias, column));
        return this;
    }

    public SqlGroupBy andJsonAsInt(CharSequence column, CharSequence property) {
        // need to reevaluate group by columns since you can group by something
        // other than columns
        columns.column(new SqlColumn(SqlAlias.of(null), SqlJson.asInt(column, property)));
        return this;
    }

    public SqlGroupBy andExpression(CharSequence expression) {
        // need to reevaluate group by columns since you can group by something
        // other than columns
        columns.column(new SqlColumn(SqlAlias.of(null), expression));
        return this;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        columns.render(sb, params);

        if (StringUtils.isNotEmpty(having)) {
            sb.append(" having ").append(having);
            if (CollectionUtils.isNotEmpty(havingParams)) {
                params.addAll(havingParams);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return columns.isEmpty();
    }

    public void replace(SqlColumn column, SqlColumn replace) {
        columns.replace(column, replace);
    }

    public SqlGroupBy withCountGreaterThan(int withCount) {
        return having("count(1) > ?", withCount);
    }

    public SqlGroupBy having(CharSequence having, Object... params) {
        this.having = having;
        if (havingParams != null) {
            havingParams.clear();
        }
        if (params != null && params.length > 0) {
            havingParams = Arrays.asList(params);
        }
        return this;
    }

}
