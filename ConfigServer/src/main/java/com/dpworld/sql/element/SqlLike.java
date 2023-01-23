package com.dpworld.sql.element;

import java.util.List;

public class SqlLike implements SqlElement {

    private CharSequence value;
    private boolean before;
    private boolean after;

    private SqlLike(CharSequence value, boolean before, boolean after) {
        this.value = value;
        this.before = before;
        this.after = after;
    }

    public static SqlLike of(CharSequence value, boolean before, boolean after) {
        return new SqlLike(value, before, after);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(" ilike '");
        if (before) {
            sb.append('%');
        }

        for (var i = 0; i < value.length(); i++) {
            var c = value.charAt(i);
            if (c == '\'' || c == '_' || c == '%' || c == '\\') {
                sb.append('\\');
            }
            sb.append(c);
        }

        if (after) {
            sb.append('%');
        }
        sb.append('\'');
    }

}
