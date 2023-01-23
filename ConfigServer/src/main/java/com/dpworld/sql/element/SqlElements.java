package com.dpworld.sql.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SqlElements<T extends SqlElement> implements SqlElement, Iterable<T> {

    private SqlElement before;
    private SqlElement between;
    private SqlElement after;
    private List<T> elements;

    @Override
    public void render(StringBuilder sb, List<Object> params) {
        if (before != null) {
            before.render(sb, params);
        }
        if (elements != null) {
            var first = true;
            for (SqlElement element : elements) {
                if (!first) {
                    if (between != null) {
                        between.render(sb, params);
                    }
                } else {
                    first = false;
                }
                element.render(sb, params);
            }
        }
        if (after != null) {
            after.render(sb, params);
        }
    }

    public SqlElements<T> add(T element) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
        return this;
    }

    public SqlElements<T> before(SqlElement before) {
        this.before = before;
        return this;
    }

    public SqlElements<T> between(SqlElement between) {
        this.between = between;
        return this;
    }

    public SqlElements<T> after(SqlElement after) {
        this.after = after;
        return this;
    }

    public boolean isEmpty() {
        return elements == null || elements.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return elements == null ? Collections.emptyIterator() : elements.iterator();
    }

    public void clear() {
        if (elements != null) {
            elements.clear();
        }
    }

    public void replace(T element, T replace) {
        if (elements != null) {
            for (var i = 0; i < elements.size(); i++) {
                if (elements.get(i).equals(element)) {
                    elements.set(i, replace);
                }
            }
        }
    }

}
