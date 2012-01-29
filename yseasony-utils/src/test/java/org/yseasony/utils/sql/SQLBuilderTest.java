package org.yseasony.utils.sql;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yseasony.utils.base.TestBase;

public class SQLBuilderTest extends TestBase{

    @Autowired
    private SQLBuilder sqlBuilder;

    @Test
    public void testGetDynamicalSql() {
        String sql = sqlBuilder.getDynamicalSql("test.dao.findOne", null);
        assertNotNull(sql);
    }

}
