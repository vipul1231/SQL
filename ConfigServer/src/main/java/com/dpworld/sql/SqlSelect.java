package com.dpworld.sql;

import com.dpworld.sql.element.*;
import com.dpworld.sql.table.ColumnNames;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelect extends SqlNode {

    private SqlAlias alias;
    private ColumnNames names = ColumnNames.empty();
    private SqlElements<SqlElementAs> elements;
    private boolean distinct;

    protected SqlSelect(SqlBuilder builder, SqlAlias alias) {
        super(builder);
        this.alias = alias;
        elements = new SqlElements<>();
        elements.between(SqlDelimiter.COMMA);
    }

    public SqlSelect clear() {
        elements.clear();
        names.clear();
        return this;
    }

    public SqlSelect count() {
        return expression("count(1)");
    }

    public SqlSelect distinct() {
        distinct = true;
        return this;
    }

    public SqlSelect expression(Number exp) {
        return expression(String.valueOf(exp));
    }

    public SqlSelect expression(CharSequence expression) {
        return add(SqlExpression.of(expression));
    }

    public SqlSelect sum(CharSequence column) {
        return window("sum({})", alias, column);
    }

    public SqlSelect sum(CharSequence alias, CharSequence column) {
        return window("sum({})", alias, column);
    }

    public SqlSelect min(CharSequence column) {
        return window("min({})", alias, column);
    }

    public SqlSelect min(CharSequence alias, CharSequence column) {
        return window("min({})", alias, column);
    }

    public SqlSelect max(CharSequence column) {
        return window("max({})", alias, column);
    }

    public SqlSelect max(CharSequence alias, CharSequence column) {
        return window("max({})", alias, column);
    }

    public SqlSelect avg(CharSequence column) {
        return window("avg({})", alias, column);
    }

    public SqlSelect avg(CharSequence alias, CharSequence column) {
        return window("avg({})", alias, column);
    }

    public SqlSelect product(CharSequence alias, CharSequence column) {
        var product = "(case when sum(case when {} = 0.0 then 1 else 0 end) > 0 then 0 else 1 end " //
                + "* exp(sum(ln(case when {} = 0.0 then 1.0 else {} end))))::numeric(18,6)";
        return window(product, alias, column);
    }

    public SqlSelect product(CharSequence expression) {
        var product = "(case when sum(case when {} = 0.0 then 1 else 0 end) > 0 then 0 else 1 end " //
                + "* exp(sum(ln(case when {} = 0.0 then 1.0 else {} end))))::numeric(18,6)";
        return expression(product.replace("{}", expression));
    }

    private SqlSelect window(CharSequence window, CharSequence alias, CharSequence column) {
        String expression;
        if (StringUtils.isNotEmpty(alias)) {
            expression = window.toString().replace("{}", alias + "." + column);
        } else {
            expression = window.toString().replace("{}", column);
        }
        expression(expression);
        return as(column);
    }

    public SqlSelect sumExpression(CharSequence exp) {
        return expression("sum(" + exp + ")");
    }

    public SqlSelect minExpression(CharSequence exp) {
        return expression("min(" + exp + ")");
    }

    public SqlSelect maxExpression(CharSequence exp) {
        return expression("max(" + exp + ")");
    }

    public SqlSelect avgExpression(CharSequence exp) {
        return expression("avg(" + exp + ")");
    }

    public SqlSelect jsonAsInt(CharSequence column, CharSequence property) {
        return expression(SqlJson.asInt(column, property)).as(property);
    }

    public SqlSelect json(ColumnNames columnNames) {
        var columns = new ArrayList<CharSequence>();
        for (String columnName : columnNames) {
            columns.add(columnName);
            columns.add(columnName);
        }
        return json(columns);
    }

    public SqlSelect json(CharSequence... columns) {
        return json(Arrays.asList(columns));
    }

    public SqlSelect json(Iterable<? extends CharSequence> columns) {
        var json = new StringBuilder();

        CharSequence property = null;

        for (CharSequence column : columns) {
            if (property == null) {
                property = column;
            } else {
                if (json.length() > 0) {
                    json.append(", ");
                }
                CharSequence alias = this.alias;
                var columnAsString = column.toString();
                int index = columnAsString.indexOf('.');
                if (index != -1) {
                    alias = columnAsString.substring(0, index);
                    column = columnAsString.substring(index + 1);
                }

                json.append('\'').append(property).append("', ");
                if (StringUtils.isNotEmpty(alias)) {
                    json.append(alias).append('.');
                }
                json.append(column);
                property = null;
            }
        }

        return expression("jsonb_build_object(" + json + ")");
    }

    public SqlSelect coalesce(CharSequence... columns) {
        var exp = new StringBuilder();
        for (CharSequence column : columns) {
            if (exp.length() > 0) {
                exp.append(", ");
            }
            CharSequence alias = this.alias;
            var columnAsString = column.toString();
            int index = columnAsString.indexOf('.');
            if (index != -1) {
                alias = columnAsString.substring(0, index);
                column = columnAsString.substring(index + 1);
            }

            if (StringUtils.isNotEmpty(alias)) {
                exp.append(alias).append('.');
            }
            exp.append(column);
        }

        return expression("coalesce(" + exp + ")");
    }

    public SqlSelect columns(String... names) {
        for (String name : names) {
            column(name);
        }
        return this;
    }

    public SqlSelect columns(Iterable<? extends CharSequence> names) {
        names.forEach(name -> column(name));
        return this;
    }

    public SqlSelect column(CharSequence name) {
        return column(alias, name);
    }

    public SqlSelect column(CharSequence alias, CharSequence name) {
        return add(SqlColumn.of(alias, name)).as(name);
    }

    private SqlSelect add(SqlElement element) {
        elements.add(new SqlElementAs(element, names.size()));
        names.add("");
        return this;
    }

    public SqlSelect value(CharSequence value) {
        return add(SqlLiteral.of(value));
    }

    public SqlSelect value(int value) {
        return add(SqlLiteral.of(Integer.valueOf(value)));
    }

    public SqlSelect value(long value) {
        return add(SqlLiteral.of(Long.valueOf(value)));
    }

    public SqlSelect value(double value) {
        return add(SqlLiteral.of(Double.valueOf(value)));
    }

    public SqlSelect value(float value) {
        return add(SqlLiteral.of(Float.valueOf(value)));
    }

    public SqlSelect value(boolean value) {
        return add(SqlLiteral.of(Boolean.valueOf(value)));
    }

    public SqlSelect value(Object value) {
        return add(SqlLiteral.of(value));
    }

    public SqlSelect as(CharSequence name) {
        names.set(names.size() - 1, name.toString());
        return this;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public ColumnNames names() {
        return names;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        if (distinct) {
            sb.append("distinct ");
        }
        elements.render(sb, params);
    }

    public void replace(SqlColumn column, SqlColumn replace) {
        for (SqlElementAs element : elements) {
            if (element.element.equals(column)) {
                element.element = replace;
            }
        }
    }

    private class SqlElementAs implements SqlElement {
        private SqlElement element;
        private int index;

        protected SqlElementAs(SqlElement element, int index) {
            this.element = element;
            this.index = index;
        }

        @Override
        public void render(StringBuilder sb, List<Object> params) {
            element.render(sb, params);
            String name = names.get(index);
            if (StringUtils.isNotEmpty(name)) {
                if (!(element instanceof SqlColumn) || !((SqlColumn) element).column().toString().equals(name)) {
                    sb.append(" as ").append(name);
                }
            }
        }
    }

}
