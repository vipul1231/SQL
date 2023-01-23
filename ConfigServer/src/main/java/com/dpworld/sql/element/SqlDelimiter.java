package com.dpworld.sql.element;

import java.util.List;

public class SqlDelimiter implements SqlElement {

    public static final SqlDelimiter COMMA = new SqlDelimiter(", ");
    public static final SqlDelimiter AND = new SqlDelimiter(" and ");
    public static final SqlDelimiter OR = new SqlDelimiter(" or ");

    private CharSequence delimiter;

    private SqlDelimiter(CharSequence delimiter) {
        this.delimiter = delimiter;
    }

    public static SqlDelimiter of(CharSequence delimiter) {
        return new SqlDelimiter(delimiter);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(delimiter);
    }

    @Override
    public String toString() {
        return String.valueOf(delimiter);
    }

}
