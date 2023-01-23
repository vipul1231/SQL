package com.dpworld.sql.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlUtils {

    public static final int STRING_BUILDER_SIZE = 64;

    public static final String UNICODE_ESCAPE = "N";
    public static final String NONUNICODE_ESCAPE = "/*Z*/";

    public static enum SqlPlaceholderType {
        PARAM,
        COMMENT,
        LITERAL;
    }

    public static class SqlPlaceholder {
        private int index;
        private String tag;
        private CharSequence value;
        private SqlPlaceholderType type;

        SqlPlaceholder(SqlPlaceholderType type, int index, CharSequence value) {
            this.type = type;
            this.index = index;
            this.value = value;
            tag = "${" + type.name().charAt(0) + "-" + index + "}";
        }

        public SqlPlaceholder(SqlPlaceholder sqlPlaceholder) {
            index = sqlPlaceholder.index;
            tag = sqlPlaceholder.tag;
            value = sqlPlaceholder.value;
            type = sqlPlaceholder.type;
        }

        public String getTag() {
            return tag;
        }

        public int getIndex() {
            return index;
        }

        public SqlPlaceholderType getType() {
            return type;
        }

        public CharSequence getValue() {
            return value;
        }

        public void setValue(CharSequence value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return tag;
        }
    }

    public static StringBuilder findPlaceholders(CharSequence sql, List<SqlPlaceholder> placeholders,
            SqlPlaceholderType... types) {
        int index = 0;
        int length = sql.length();
        StringBuilder result = new StringBuilder(SqlUtils.STRING_BUILDER_SIZE);
        char prevChar = ' ';
        int commentIndex = 0;
        int literalIndex = 0;
        int paramIndex = 0;
        boolean replaceComments = isEmpty(types) || in(types, SqlPlaceholderType.COMMENT);
        boolean replaceLiterals = isEmpty(types) || in(types, SqlPlaceholderType.LITERAL);
        boolean replaceParams = isEmpty(types) || in(types, SqlPlaceholderType.PARAM);
        int nuIndex = 0;

        while ((index < length) && (sql.charAt(index) == ' ')) {
            index++;
        }

        while ((length > 0) && (sql.charAt(length - 1) == ' ')) {
            length--;
        }

        while (index < length) {
            char c = sql.charAt(index);
            boolean appendChar = true;

            if (c == '\'' || ((c == 'N' || c == 'x') && lookAhead(sql, index + 1) == '\'')) {
                int endIndex = indexOf(sql, index + 1 + (c != '\'' ? 1 : 0), '\'');
                if (endIndex != -1) {
                    CharSequence literal = sql.subSequence(index, endIndex + 1);
                    if (nuIndex == index - 1) {
                        literal = NONUNICODE_ESCAPE + literal;
                    }
                    SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.LITERAL, literalIndex++,
                            literal);
                    placeholders.add(placeholder);
                    result.append(placeholder);
                    index = endIndex;
                    appendChar = false;
                }
                if (appendChar && (nuIndex == index - 1)) {
                    result.append(NONUNICODE_ESCAPE);
                }
            } else if (c == '@' || (c == '-' && lookAhead(sql, index + 1) == '-')) {
                int endIndex = indexOf(sql, index + 1, '\r', '\n');
                if (endIndex != -1) {
                    CharSequence comment = sql.subSequence(index, endIndex + 1);
                    SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.COMMENT, commentIndex++,
                            comment);
                    placeholders.add(placeholder);
                    result.append(placeholder);
                    index = endIndex;
                    appendChar = false;
                } else {
                    CharSequence comment = sql.subSequence(index, sql.length());
                    SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.COMMENT, commentIndex++,
                            comment);
                    placeholders.add(placeholder);
                    result.append(placeholder);
                    index = length;
                    appendChar = false;
                }
            } else if ((c == 'R' && lookAhead(sql, index + 1) == 'E' && lookAhead(sql, index + 2) == 'M')
                    && isNewLineOrSpace(prevChar) && isNewLineOrSpace(lookAhead(sql, index + 3))) {
                int endIndex = indexOf(sql, index + 1, '\r', '\n');
                if (endIndex != -1) {
                    CharSequence comment = sql.subSequence(index, endIndex + 1);
                    SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.COMMENT, commentIndex++,
                            comment);
                    placeholders.add(placeholder);
                    result.append(placeholder);
                    index = endIndex;
                    appendChar = false;
                } else {
                    CharSequence comment = sql.subSequence(index, sql.length());
                    SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.COMMENT, commentIndex++,
                            comment);
                    placeholders.add(placeholder);
                    result.append(placeholder);
                    index = length;
                    appendChar = false;
                }
            } else if (c == '/' && lookAhead(sql, index + 1) == '*') {
                int endIndex = indexOf(sql, index + 1, '*');
                if (endIndex != -1) {
                    CharSequence comment = sql.subSequence(index, endIndex + 1);
                    if (!comment.equals(NONUNICODE_ESCAPE)) {
                        SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.COMMENT, commentIndex++,
                                comment);
                        placeholders.add(placeholder);
                        result.append(placeholder);
                    } else {
                        nuIndex = endIndex;
                    }
                    index = endIndex;
                    appendChar = false;
                }
            } else if (c == '?') {
                CharSequence param = sql.subSequence(index, index + 1);
                SqlPlaceholder placeholder = new SqlPlaceholder(SqlPlaceholderType.PARAM, paramIndex++, param);
                placeholders.add(placeholder);
                result.append(placeholder);
                appendChar = false;
            }

            if (appendChar) {
                if (c == '\r' || c == '\n') {
                    c = ' ';
                }
                if (c == ' ' && result.length() > 0) {
                    char lastChar = result.charAt(result.length() - 1);
                    if (lastChar == ' ' || lastChar == '(') {
                        appendChar = false;
                    }
                }
                if (appendChar) {
                    result.append(c);
                }
                prevChar = c;
            } else {
                prevChar = ' ';
            }

            index++;
        }

        if (!replaceComments) {
            replace(result, placeholders, SqlPlaceholderType.COMMENT);
            for (int i = placeholders.size() - 1; i >= 0; i--) {
                if (placeholders.get(i).getType() == SqlPlaceholderType.COMMENT) {
                    placeholders.remove(i);
                }
            }
        }
        if (!replaceLiterals) {
            replace(result, placeholders, SqlPlaceholderType.LITERAL);
            for (int i = placeholders.size() - 1; i >= 0; i--) {
                if (placeholders.get(i).getType() == SqlPlaceholderType.LITERAL) {
                    placeholders.remove(i);
                }
            }
        }
        if (!replaceParams) {
            replace(result, placeholders, SqlPlaceholderType.PARAM);
            for (int i = placeholders.size() - 1; i >= 0; i--) {
                if (placeholders.get(i).getType() == SqlPlaceholderType.PARAM) {
                    placeholders.remove(i);
                }
            }
        }

        return result;
    }

    private static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    private static boolean in(SqlPlaceholderType[] types, SqlPlaceholderType searchType) {
        for (SqlPlaceholderType type : types) {
            if (type == searchType) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNewLine(char c) {
        return c == '\r' || c == '\n';
    }

    private static boolean isNewLineOrSpace(char c) {
        return c == ' ' || isNewLine(c);
    }

    private static int indexOf(CharSequence sql, int index, char... searchChars) {
        int result = -1;

        int length = sql.length();
        while (index < length) {
            char c = sql.charAt(index);
            for (char searchChar : searchChars) {
                if (c == searchChar) {
                    if (c == '\'' && lookAhead(sql, index + 1) == '\'') {
                        index++;
                    } else if (c == '*') {
                        if (lookAhead(sql, index + 1) == '/') {
                            return index + 1;
                        }
                    } else {
                        if (c == '\r' && lookAhead(sql, index + 1) == '\n') {
                            return index + 1;
                        }
                        if (c == '\n' && lookAhead(sql, index + 1) == '\r') {
                            return index + 1;
                        }
                        return index;
                    }
                }
            }
            index++;
        }
        return result;
    }

    private static char lookAhead(CharSequence sql, int index) {
        if (index < sql.length()) {
            return sql.charAt(index);
        }
        return ' ';
    }

    public static void replace(StringBuilder sql, List<SqlPlaceholder> placeholders, SqlPlaceholderType... types) {
        int index = 0;

        for (SqlPlaceholder placeholder : placeholders) {
            if (isEmpty(types) || in(types, placeholder.getType())) {
                String tag = placeholder.getTag();
                int searchIndex = sql.indexOf(tag, index);
                if (searchIndex != -1) {
                    CharSequence value = placeholder.getValue();
                    if (value != null) {
                        sql.replace(searchIndex, searchIndex + tag.length(), value.toString());

                        if (searchIndex > 0) {
                            int lo = searchIndex - 1;
                            while (lo >= 0) {
                                if (sql.charAt(lo) != ' ') {
                                    break;
                                }
                                lo--;
                            }
                            lo++;

                            int hi = searchIndex - 1;
                            while (hi < sql.length()) {
                                if (sql.charAt(hi) != ' ') {
                                    break;
                                }
                                hi++;
                            }

                            if (lo < hi) {
                                sql.replace(lo, hi, " ");
                            }
                        }
                    }
                    index = searchIndex;
                }
            }
        }
    }

    public static void removeLastComma(StringBuilder sql) {
        for (int i = sql.length() - 1; i >= 0; i--) {
            char c = sql.charAt(i);
            if (c == ',') {
                sql.setLength(i);
                break;
            } else if (c != ' ') {
                break;
            }
        }
    }

    public static void andWhere(StringBuilder sql, CharSequence whereSql) {
        if (whereSql != null) {
            whereSql = StringUtils.removeStartIgnoreCase(whereSql.toString().trim(), "and").trim();
        }

        if (StringUtils.isNotEmpty(whereSql)) {
            if (sql.length() > 0) {
                sql.append(" and ");
            }
            sql.append(whereSql);
        }
    }

    public static void appendWhere(StringBuilder sql, CharSequence whereSql) {
        if (whereSql != null) {
            whereSql = StringUtils.removeStartIgnoreCase(whereSql.toString().trim(), "where").trim();
        }

        if (StringUtils.isNotEmpty(whereSql)) {
            if (!StringUtils.startsWithIgnoreCase(whereSql, "left ")
                    && !StringUtils.startsWithIgnoreCase(whereSql, "right ")
                    && !StringUtils.startsWithIgnoreCase(whereSql, "join ")) {
                sql.append(" where ");
            } else {
                sql.append(" ");
            }
            sql.append(whereSql);
        }
    }

    public static String join(Object[] values) {
        StringBuilder result = new StringBuilder();
        for (Object value : values) {
            if (result.length() > 0) {
                result.append(", ");
            }
            if (value instanceof Enum) {
                value = ((Enum<?>) value).name();
            }

            if (value instanceof String || value instanceof LocalDate || value instanceof LocalDateTime) {
                result.append('\'').append(value.toString()).append('\'');
            } else {
                result.append(value.toString());
            }
        }
        return result.toString();
    }

    public static String getRowNumberSql(long offset, Iterable<? extends CharSequence> columns) {
        return getRowNumberSql(offset, columns, null);
    }

    public static String getRowNumberSql(long offset, Iterable<? extends CharSequence> columns, String alias) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence column : columns) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            if (StringUtils.isNotEmpty(alias)) {
                sb.append(alias).append('.');
            }
            sb.append(column);
        }
        return getRowNumberSql(offset, sb.toString());
    }

    public static String getRowNumberSql(long offset, String columns) {
        String result = "row_number() over (order by " + columns + ")";
        if (--offset != 0) {
            if (offset < 0) {
                result += " - " + (-offset);
            } else {
                result += " + " + offset;
            }
        }
        return result;
    }

    public static List<CharSequence> findEncapsulated(CharSequence sql, StringBuilder sb) {
        List<CharSequence> result = null;
        StringBuilder replaced = new StringBuilder();

        int i = 0;
        int length = sql.length();
        int count = 0;
        sb.setLength(0);

        while (i < length) {
            char c = sql.charAt(i);

            if (c == '\'') {
                int beginIndex = i;
                i++;
                int nCount = 0;

                while (i < length - 1) {
                    c = sql.charAt(i);
                    if (c == '\'') {
                        nCount++;
                        if (sql.charAt(i + 1) != '\'') {
                            if (nCount % 2 != 0) {
                                break;
                            }
                        }
                    }
                    i++;
                }
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(sql.subSequence(beginIndex, i + 1));
                sb.append("${E-").append(count).append("}");
                count++;
            } else if (c == '(') {
                int iLevel = 0;
                replaced.setLength(0);
                replaced.append(c);
                i++;

                while (i < length) {
                    c = sql.charAt(i);
                    replaced.append(c);

                    if (c == '\'') {
                        i++;
                        int nCount = 0;

                        while (i < length) {
                            c = sql.charAt(i);
                            replaced.append(c);
                            if ((c == '\'') && (i != (length - 1))) {
                                nCount++;
                                if (sql.charAt(i + 1) != '\'') {
                                    if (nCount % 2 != 0) {
                                        break;
                                    }
                                }
                            }
                            i++;
                        }
                    } else if (c == ')') {
                        if (iLevel == 0) {
                            break;
                        }
                        iLevel--;
                    } else if (c == '(') {
                        iLevel++;
                    }
                    i++;
                }
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add("(" + replaced.substring(1, replaced.length() - 1).trim() + ")");
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
                sb.append("${E-").append(count).append("}");
                count++;
            } else {
                if (c == ' ') {
                    if ((sb.length() == 0) || (sb.charAt(sb.length() - 1) != ' ')) {
                        sb.append(c);
                    }
                } else {
                    sb.append(c);
                }
            }
            i++;
        }

        return result == null ? Collections.emptyList() : result;
    }

    public static String format(String s, Object... params) {
        String result = s.formatted(params).replace("\r", " ").replace("\n", " ").replace("( ", "(").replace(" )", ")");
        String stripped = result.replace("  ", "").trim();
        while (!result.equals(stripped)) {
            result = stripped;
            stripped = result.replace("  ", "").trim();
        }
        return result;
    }

}
