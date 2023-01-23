package com.dpworld.sql.element;

import java.util.List;
import java.util.Objects;

public class SqlAlias implements CharSequence, SqlElement {

    private CharSequence name;

    protected SqlAlias(CharSequence name) {
        this.name = name;
    }

    public static SqlAlias of(CharSequence name) {
        if (name instanceof SqlAlias) {
            return (SqlAlias) name;
        }
        return new SqlAlias(name);
    }

    public CharSequence name() {
        return name;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        SqlElement.render(name, sb, params);
    }

    @Override
    public int length() {
        return name == null ? 0 : name.length();
    }

    @Override
    public char charAt(int index) {
        return name.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return name.subSequence(start, end);
    }

    public boolean isNull() {
        return name == null;
    }

    public boolean isNotNull() {
        return name != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SqlAlias) {
            SqlAlias other = (SqlAlias) obj;
            return Objects.equals(name, other.name);
        }
        if (obj instanceof CharSequence) {
            return Objects.equals(name, obj);
        }
        return false;
    }

    @Override
    public String toString() {
        return toString(true);
    }

}
