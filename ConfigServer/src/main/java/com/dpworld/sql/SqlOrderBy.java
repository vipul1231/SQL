package com.dpworld.sql;

import com.dpworld.sql.element.*;

import java.util.List;

public class SqlOrderBy extends SqlNode {

    private SqlAlias alias;
    private SqlElements<SqlColumnAndDirection> elements = new SqlElements<>();

    protected SqlOrderBy(SqlBuilder builder, SqlAlias alias) {
        super(builder);
        this.alias = alias;
        elements.between(SqlDelimiter.COMMA);
    }

    public SqlOrderBy desc(CharSequence column) {
        return desc(alias, column);
    }

    public SqlOrderBy desc(CharSequence alias, CharSequence column) {
        elements.add(SqlColumnAndDirection.of(SqlColumn.of(alias, column), SqlDirection.DESC));
        return this;
    }

    public SqlOrderBy desc(Iterable<? extends CharSequence> columns) {
        for (CharSequence column : columns) {
            desc(column);
        }
        return this;
    }

    public SqlOrderBy desc(CharSequence alias, Iterable<? extends CharSequence> columns) {
        for (CharSequence column : columns) {
            desc(alias, column);
        }
        return this;
    }

    public SqlOrderBy asc(CharSequence column) {
        return asc(alias, column);
    }

    public SqlOrderBy asc(Iterable<? extends CharSequence> columns) {
        for (CharSequence column : columns) {
            asc(column);
        }
        return this;
    }

    public SqlOrderBy asc(CharSequence alias, Iterable<? extends CharSequence> columns) {
        for (CharSequence column : columns) {
            asc(alias, column);
        }
        return this;
    }

    public SqlOrderBy asc(CharSequence alias, CharSequence column) {
        elements.add(SqlColumnAndDirection.of(SqlColumn.of(alias, column), SqlDirection.ASC));
        return this;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        elements.render(sb, params);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public SqlOrderBy clear() {
        elements.clear();
        return this;
    }

}