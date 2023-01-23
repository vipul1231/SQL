package com.dpworld.sql.element;

import java.util.List;

public class SqlWrapper implements SqlElement {

    private SqlElement element;
    private CharSequence before;
    private CharSequence after;

    private SqlWrapper(SqlElement element, CharSequence before, CharSequence after) {
        this.element = element;
        this.before = before;
        this.after = after;
    }

    public static SqlWrapper parens(SqlElement element) {
        return new SqlWrapper(element, "(", ")");
    }

    public static SqlWrapper singleQuote(SqlElement element) {
        return new SqlWrapper(element, "'", "'");
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(before);
        element.render(sb, params);
        sb.append(after);
    }

}
