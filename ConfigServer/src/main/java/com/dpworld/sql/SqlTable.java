package com.dpworld.sql;

import com.dpworld.sql.element.SqlAlias;
import com.dpworld.sql.element.SqlAliasElement;
import com.dpworld.sql.element.SqlColumns;
import org.h2.table.Table;

import java.util.List;
import java.util.Objects;

public class SqlTable implements SqlAliasElement {

    private CharSequence name;
    private Table table;
    private SqlAlias alias;

    private SqlTable(CharSequence name, SqlAlias alias) {
        this.name = name    ;
        this.alias = alias;
        if (name instanceof Table) {
            table = (Table) name;
        }
    }

    public static SqlTable of(CharSequence name, CharSequence alias) {
        return new SqlTable(name, SqlAlias.of(alias));
    }

    public CharSequence name() {
        return name;
    }

    @Override
    public SqlAlias alias() {
        return alias;
    }

    public Table table() {
        if (table == null) {
            //table = ContextUtils.getBean("defaultTableService", TableService.class).findTable(name.toString());
        }
        return table;
    }

    public SqlColumn column(CharSequence column) {
        return SqlColumn.of(column, alias);
    }

    public SqlColumns columns(CharSequence... columns) {
        var result = new SqlColumns();
        for (CharSequence column : columns) {
            result.column(alias, column);
        }
        return result;
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        sb.append(name);
        if (alias.isNotNull()) {
            sb.append(' ');
            alias.render(sb, params);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SqlTable)) {
            return false;
        }
        SqlTable other = (SqlTable) obj;
        return Objects.equals(name, other.name) && Objects.equals(alias, other.alias);
    }

}
