package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlElement;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class SqlColumn implements SqlElement {

    private SqlAlias alias;
    private CharSequence column;

    protected SqlColumn(SqlAlias alias, CharSequence column) {
        this.alias = alias;
        this.column = column;
    }

    public static SqlColumn of(CharSequence alias, CharSequence column) {
        var columnAsString = column.toString();
        int index = columnAsString.indexOf(".");
        if (index != -1) {
            alias = columnAsString.substring(0, index);
            column = columnAsString.substring(index + 1);
        }
        return new SqlColumn(SqlAlias.of(alias), column);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        if (alias.isNotNull()) {
            alias.render(sb, params);
            sb.append('.');
        }
        SqlElement.render(column, sb, params);
    }

    public SqlAlias alias() {
        return alias;
    }

    public CharSequence column() {
        return column;
    }

    public static CharSequence combine(CharSequence alias, CharSequence column) {
        return StringUtils.isEmpty(alias) ? column : alias + "." + column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SqlColumn)) {
            return false;
        }
        SqlColumn other = (SqlColumn) obj;
        return Objects.equals(alias, other.alias) && Objects.equals(column, other.column);
    }

    @Override
    public String toString() {
        return toString(true);
    }

}
