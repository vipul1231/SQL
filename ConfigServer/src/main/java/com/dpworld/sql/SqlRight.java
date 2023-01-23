package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlExpression;
import com.dpworld.sql.element.SqlLiteral;

public class SqlRight {

    private SqlWhere where;

    protected SqlRight(SqlWhere where) {
        this.where = where;
    }

    public SqlWhere column(CharSequence column) {
        return column(where.rightAlias(), column);
    }

    public SqlWhere column(CharSequence alias, CharSequence column) {
        return where.add(SqlColumn.of(alias, column));
    }

    public SqlWhere expression(CharSequence expression) {
        return where.add(SqlExpression.of(expression));
    }

    public SqlWhere literal(Object value) {
        return where.add(SqlLiteral.of(value));
    }

    public SqlAlias alias() {
        return where.rightAlias();
    }

}
