package com.dpworld.sql;

import com.dpworld.sql.element.*;

import java.util.List;

public class SqlSet implements SqlElement {

    private SqlAlias rightAlias;
    private SqlUpdate updateSql;
    private SqlElements<SqlElement> elements;

    public SqlSet(SqlUpdate updateSql, SqlAlias rightAlias) {
        this.updateSql = updateSql;
        this.rightAlias = rightAlias;
        elements = new SqlElements<>();
        elements.between(SqlDelimiter.COMMA);
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean isNotEmpty() {
        return elements.isNotEmpty();
    }

    public SetColumn column(CharSequence column) {
        var result = new SetColumn(column);
        elements.add(result);
        return result;
    }

    public class SetColumn implements SqlElement {
        private CharSequence column;
        private SqlElement element;

        protected SetColumn(CharSequence column) {
            this.column = column;
        }

        public SqlUpdate column(CharSequence column) {
            element = SqlColumn.of(rightAlias, column);
            return updateSql;
        }

        public SqlUpdate column(CharSequence alias, CharSequence column) {
            element = SqlColumn.of(alias, column);
            return updateSql;
        }

        public SqlUpdate value(Object value) {
            if (value == null) {
                element = SqlExpression.of("null");
            } else {
                element = SqlLiteral.of(value);
            }
            return updateSql;
        }

        public SqlUpdate expression(String expression, Object... params) {
            element = SqlExpression.of(expression, params);
            return updateSql;
        }

        @Override
        public void render(StringBuilder sb, List<Object> params) {
            sb.append(column).append(" = ");
            element.render(sb, params);
        }
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        elements.render(sb, params);
    }

}
