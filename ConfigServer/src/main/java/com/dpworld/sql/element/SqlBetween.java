package com.dpworld.sql.element;

import java.util.List;

public class SqlBetween implements SqlElement {

    private Object min;
    private Object max;

    private SqlBetween(Object min, Object max) {
        this.min = min;
        this.max = max;
    }

    public static SqlBetween of(Object min, Object max) {
        return new SqlBetween(min, max);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(" between ? and ?");
        params.add(min);
        params.add(max);
    }

}
