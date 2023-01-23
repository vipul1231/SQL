package com.dpworld.sql.element;

import java.util.List;

public class SqlDirection implements SqlElement {

    public static final SqlDirection ASC = new SqlDirection("asc");
    public static final SqlDirection DESC = new SqlDirection("desc");

    private CharSequence direction;

    private SqlDirection(CharSequence direction) {
        this.direction = direction;
    }

    public CharSequence direction() {
        return direction;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(direction);
    }

    public boolean isAscending() {
        return direction == ASC;
    }

    public boolean isDesending() {
        return direction == DESC;
    }

}
