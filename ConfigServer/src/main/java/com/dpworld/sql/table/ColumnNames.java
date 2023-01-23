package com.dpworld.sql.table;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ColumnNames implements Iterable<String> {

    private List<String> names;

    public static ColumnNames empty() {
        return new ColumnNames();
    }

    public static ColumnNames of(CharSequence... columnNames) {
        return new ColumnNames().add(columnNames);
    }

    public static ColumnNames of(Iterable<? extends CharSequence> columnNames) {
        ColumnNames result = new ColumnNames();
        columnNames.forEach(columnName -> result.add(columnName));
        return result;
    }

    public ColumnNames add(CharSequence... columnNames) {
        if (columnNames != null && columnNames.length > 0) {
            for (CharSequence columnName : columnNames) {
                if (columnName == null) {
                    continue;
                }

                if (columnName.length() == 0) {
                    addName(columnName.toString());
                } else {
                    StringBuilder sb = new StringBuilder();
                    int parenCount = 0;

                    for (int i = 0; i < columnName.length(); i++) {
                        char c = columnName.charAt(i);

                        if (c == '(') {
                            parenCount++;
                            sb.append(c);
                        } else if (c == ')') {
                            parenCount--;
                            sb.append(c);
                            if (parenCount < 0) {
                                parenCount = 0;
                            }
                        } else if (c == ',' && parenCount == 0) {
                            if (sb.length() > 0) {
                                addName(sb.toString());
                                sb.setLength(0);
                            }
                        } else {
                            sb.append(c);
                        }
                    }

                    if (sb.length() > 0) {
                        addName(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
        }
        return this;
    }

    private void addName(String name) {
        if (names == null) {
            names = new ArrayList<>();
        }
        names.add(name.trim());
    }

    public ColumnNames add(Iterable<? extends CharSequence> columnNames) {
        columnNames.forEach(columnName -> add(columnName));
        return this;
    }

    public ColumnNames remove(String columnName) {
        if (names != null) {
            names.remove(columnName);
        }
        return this;
    }

    public ColumnNames remove(ColumnNames columnNames) {
        columnNames.forEach(columnName -> remove(columnName));
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        return names == null ? Collections.emptyIterator() : names.iterator();
    }

    public Stream<String> stream() {
        return names == null ? Stream.empty() : names.stream();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public int size() {
        return names == null ? 0 : names.size();
    }

    public String get(int index) {
        return names == null ? null : names.get(index);
    }

    public int indexOf(String name) {
        if (CollectionUtils.isNotEmpty(names)) {
            name = deJsonify(name);
            for (int i = 0; i < names.size(); i++) {
                String otherName = deJsonify(names.get(i));
                if (otherName.equalsIgnoreCase(name)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(String name) {
        return indexOf(name) != -1;
    }

    public boolean notContains(String name) {
        return indexOf(name) == -1;
    }

    private String deJsonify(String name) {
        int index = name.indexOf("::");
        if (index != -1) {
            name = name.substring(0, index);
        }
        return name.replace(" ", "").replace("(", "").replace(")", "");
    }

    @Override
    public String toString() {
        return toString(null);
    }

    public String toString(String alias) {
        StringBuilder result = new StringBuilder();
        forEach(columnName -> {
            if (result.length() > 0) {
                result.append(", ");
            }
            if (StringUtils.isNotEmpty(alias)) {
                result.append(alias).append('.');
            }
            result.append(columnName);
        });
        return result.toString();
    }

    public static boolean isEmpty(ColumnNames columnNames) {
        return columnNames == null || columnNames.isEmpty();
    }

    public static boolean isNotEmpty(ColumnNames columnNames) {
        return columnNames != null && columnNames.isNotEmpty();
    }

    public void clear() {
        if (names != null) {
            names.clear();
        }
    }

    public void set(int index, String name) {
        if (names != null && names.size() >= index) {
            names.set(index, name);
        }
    }

}
