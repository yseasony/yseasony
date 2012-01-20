package org.yseasony.utils.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yseasony.utils.web.VelocityUtils;

public class SQLBuilder {

    private static Logger logger = LoggerFactory.getLogger(SQLBuilder.class);

    private static Map<String, String> sqlContainer = null;

    private String path = "sql/";

    private List<String> sqlFilePaths;

    public SQLBuilder() {
        initSqlContainer();
    }

    public SQLBuilder(String path) {
        this.path = path;
        initSqlContainer();
    }

    public String getDynamicalSql(String key, Map<String, ?> param) {
        String sql = sqlContainer.get(key);
        return VelocityUtils.render(sql, param);
    }

    private void initSqlContainer() {
        if (sqlFilePaths == null) {
            throw new IllegalArgumentException("sql语句文件不能为空!");
        }
        sqlContainer = new ConcurrentHashMap<String, String>();
        for (String sqlFilePath : sqlFilePaths) {
            readSQLFromFile(path + sqlFilePath);
        }
    }

    @SuppressWarnings("unchecked")
    private void readSQLFromFile(String fileName) {
        InputStream ips = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName);
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(ips);
        } catch (DocumentException e) {
            logger.error("读取系统中用到的SQL 语句XML出错");
            throw new RuntimeException("读取sql语句XML文件出错:" + e.getMessage());
        }
        Element root = document.getRootElement();
        List<Element> sqlElements = root.selectNodes("//sqlElement");
        String key;
        for (Element sql : sqlElements) {
            key = sql.attribute("key").getValue();
            if (sqlContainer.containsKey(key)) {
                logger.warn("key值:" + key + "重复");
            }
            sqlContainer.put(key, sql.getText());

        }

        if (ips != null) {
            try {
                ips.close();
            } catch (IOException e) {
                logger.error("关闭输入流出错:" + e.getMessage());
            }
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSqlFilePaths(List<String> sqlFilePaths) {
        this.sqlFilePaths = sqlFilePaths;
    }

}