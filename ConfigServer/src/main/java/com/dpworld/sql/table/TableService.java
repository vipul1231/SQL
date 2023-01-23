package com.dpworld.sql.table;



import org.h2.table.Column;
import org.h2.table.Table;

import java.util.List;
import java.util.function.Supplier;

public interface TableService {

    Table findTable(String tableName);

    Column findColumn(String tableName, String columnName);

    boolean exists(String tableName);

    boolean notExists(String tableName);

    void lock(String tableName, Runnable callback);

    <T> T lock(String tableName, Supplier<T> callback);

    List<Table> findTables(String schema);

}
