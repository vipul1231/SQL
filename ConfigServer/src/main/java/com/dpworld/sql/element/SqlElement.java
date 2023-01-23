package com.dpworld.sql.element;

import com.dpworld.sql.utils.SqlUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface SqlElement {

    void render(StringBuilder sb, List<Object> params);

    default SqlElement append(SqlElement other) {
        return other == null ? this : (sb, params) -> {
            render(sb, params);
            other.render(sb, params);
        };
    }

    public static void render(CharSequence element, StringBuilder sb, List<Object> params) {
        if (element instanceof SqlElement) {
            ((SqlElement) element).render(sb, params);
        } else if (element != null) {
            sb.append(element);
        }
    }

    default String toString(boolean resolveParams) {
        var result = new StringBuilder();
        var params = new ArrayList<>();
        render(result, params);

        if (resolveParams && !params.isEmpty()) {
            List<SqlUtils.SqlPlaceholder> placeholders = new ArrayList<>();
            StringBuilder sb = SqlUtils.findPlaceholders(result, placeholders, SqlUtils.SqlPlaceholderType.PARAM);
            int length = Math.min(params.size(), placeholders.size());
            for (var i = 0; i < length; i++) {
                Object value = params.get(i);
                String strValue;
                if (value instanceof Enum<?>) {
                    strValue = "'" + ((Enum<?>) value).name() + "'";
                } else if (value instanceof String || value instanceof LocalDate || value instanceof LocalDateTime) {
                    strValue = "'" + value + "'";
                } else {
                    strValue = value.toString();
                }
                placeholders.get(i).setValue(strValue);
            }
            SqlUtils.replace(sb, placeholders);
            result = sb;
        }

        return result.toString();
    }

}
