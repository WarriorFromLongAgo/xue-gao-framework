package com.xuegao.mapper.model;


import java.util.HashSet;
import java.util.Set;

public class GenericModelField {
    public static String FILED_ID = "id";

    public static String FILED_SQL_COLUMN_DEL_FLAG = "del_flag";
    public static String FILED_SQL_COLUMN_CREATED_BY = "created_by";
    public static String FILED_SQL_COLUMN_CREATED_TIME = "created_time";
    public static String FILED_SQL_COLUMN_UPDATED_BY = "updated_by";
    public static String FILED_SQL_COLUMN_UPDATED_TIME = "updated_time";
    public static String FILED_SQL_COLUMN_TRACE_ID = "trace_id";

    public static String FILED_COLUMN_DEL_FLAG = "delFlag";
    public static String FILED_COLUMN_CREATED_BY = "createdBy";
    public static String FILED_COLUMN_CREATED_TIME = "createdTime";
    public static String FILED_COLUMN_UPDATED_BY = "updatedBy";
    public static String FILED_COLUMN_UPDATED_TIME = "updatedTime";
    public static String FILED_COLUMN_TRACE_ID = "traceId";

    private static Set<String> SQL_SET = new HashSet<>(10);
    private static Set<String> COLUMN_SET = new HashSet<>(10);

    static {
        SQL_SET.add(FILED_ID);
        SQL_SET.add(FILED_SQL_COLUMN_DEL_FLAG);
        SQL_SET.add(FILED_SQL_COLUMN_CREATED_BY);
        SQL_SET.add(FILED_SQL_COLUMN_CREATED_TIME);
        SQL_SET.add(FILED_SQL_COLUMN_UPDATED_BY);
        SQL_SET.add(FILED_SQL_COLUMN_UPDATED_TIME);
        SQL_SET.add(FILED_SQL_COLUMN_TRACE_ID);

        COLUMN_SET.add(FILED_ID);
        COLUMN_SET.add(FILED_COLUMN_DEL_FLAG);
        COLUMN_SET.add(FILED_COLUMN_CREATED_BY);
        COLUMN_SET.add(FILED_COLUMN_CREATED_TIME);
        COLUMN_SET.add(FILED_COLUMN_UPDATED_BY);
        COLUMN_SET.add(FILED_COLUMN_UPDATED_TIME);
        COLUMN_SET.add(FILED_COLUMN_TRACE_ID);
    }

    public static Set<String> sqlSet() {
        return SQL_SET;
    }

    public static Set<String> columnSet() {
        return COLUMN_SET;
    }
}
