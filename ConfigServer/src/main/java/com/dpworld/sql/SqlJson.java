package com.dpworld.sql;

import java.util.Arrays;

public class SqlJson {

    private SqlJson() {
    }

    public static CharSequence asInt(CharSequence columnName, CharSequence... properties) {
        return asInt(columnName, Arrays.asList(properties));
    }

    public static CharSequence asInt(CharSequence columnName, Iterable<? extends CharSequence> properties) {
        StringBuilder result = new StringBuilder();
        for (CharSequence property : properties) {
            result.append('(').append(columnName).append("->>'").append(property).append("')::int");
        }
        return result;
    }

}
