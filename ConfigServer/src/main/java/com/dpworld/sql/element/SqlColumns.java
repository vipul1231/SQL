package com.dpworld.sql.element;

import com.dpworld.sql.SqlColumn;

public class SqlColumns extends SqlElements<SqlColumn> {

    public SqlColumns() {
        between(SqlDelimiter.COMMA);
    }

    public SqlColumns column(SqlColumn column) {
        add(column);
        return this;
    }

    public SqlColumns column(CharSequence alias, CharSequence column) {
        column(SqlColumn.of(alias, column));
        return this;
    }

}
