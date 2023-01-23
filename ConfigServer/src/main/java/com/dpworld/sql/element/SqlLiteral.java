package com.dpworld.sql.element;

public class SqlLiteral extends SqlExpression {

    private SqlLiteral(Object value) {
        super("?", value);
    }

    public static SqlLiteral of(Object value) {
        return new SqlLiteral(value);
    }

}