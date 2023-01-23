package com.dpworld.sql.element;

import java.util.Collection;
import java.util.List;

public class SqlIn implements SqlElement {

    private Collection<?> values;

    private SqlIn(Collection<?> values) {
        this.values = values;
    }

    public static SqlIn of(Collection<?> values) {
        return new SqlIn(values);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(" in (");

        for (var value : values) {
            if (sb.charAt(sb.length() - 1) == '?') {
                sb.append(',');
            }
            sb.append('?');
            params.add(value);
        }

        sb.append(')');
    }

}
