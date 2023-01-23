package sql;

import com.dpworld.sql.SqlBuilder;
import com.dpworld.sql.SqlSelect;
import com.dpworld.sql.SqlTable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlBuilderTest {

    @Test
    public void testQueryBuilder() {
        SqlTable sqlTable = SqlTable.of("DCRepository","dc");

        StringBuilder sb = new StringBuilder();
        List<Object> objects = new ArrayList<>();
//        sqlTable.render(sb, objects);
        System.out.println(sqlTable.toString(true));


        SqlBuilder sqlBuilder = SqlBuilder.from(sqlTable);
        SqlSelect sqlSelect  = sqlBuilder.select();
        sqlSelect.columns(Arrays.asList("identifier","identifier2"));
        sqlBuilder.render(sb, objects);
        System.out.println(sqlBuilder);
    }
}
