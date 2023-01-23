package com.dpworld.sql.element;

import java.util.List;

public class SqlExpression implements SqlElement, CharSequence {

    private CharSequence expression;
    private Object[] params;

    protected SqlExpression(CharSequence expression, Object... params) {
        this.expression = expression;
        this.params = params;
    }

    public static SqlExpression of(CharSequence expression, Object... params) {
        return new SqlExpression(expression, params);
    }

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        if (expression != null) {
            if (expression instanceof SqlElement) {
                ((SqlElement) expression).render(sb, params);
            } else {
                sb.append(expression);
            }
        }
        if (this.params != null) {
            for (Object param : this.params) {
                params.add(param);
            }
        }
    }

    @Override
    public int length() {
        return expression.length();
    }

    @Override
    public char charAt(int index) {
        return expression.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return expression.subSequence(start, end);
    }

    public CharSequence expression() {
        return expression;
    }

    public Object[] params() {
        return params;
    }

}
