package com.dpworld.sql.element;

import com.dpworld.sql.SqlColumn;

import java.util.List;

public class SqlColumnAndDirection implements SqlElement {

    private SqlColumn column;
    private SqlDirection direction;

    private SqlColumnAndDirection(SqlColumn column, SqlDirection direction) {
        this.column = column;
        this.direction = direction;
    }

    public static SqlColumnAndDirection of(SqlColumn column, SqlDirection direction) {
        return new SqlColumnAndDirection(column, direction);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
    	column.render(sb,params);
        sb.append(' ');
        direction.render(sb,params);
    }

    public SqlColumn column() {
        return column;
    }

    public SqlDirection direction() {
        return direction;
    }

}
