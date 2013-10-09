package org.yseasony.edm.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLBuilder {

    private static Logger              logger       = LoggerFactory.getLogger(SQLBuilder.class);

    private static Map<String, String> sqlContainer = new ConcurrentHashMap<String, String>();

    private String                     path         = "sql/";

    private List<String>               sqlFilePaths;

    public String getDynamicalSql(String key, Map<String, ?> param) {
        String sql = sqlContainer.get(key);
        if (sql == null) {
            throw new RuntimeException("sql not found");
        }
        return VelocityUtils.render(sql, param);
    }

    public void init() {
        logger.info("SQLBuilder init");
        if (sqlFilePaths == null) {
            throw new IllegalArgumentException("sql语句文件不能为空!");
        }
        for (String sqlFilePath : sqlFilePaths) {
            readSQLFromFile(path + sqlFilePath);
        }
    }

    @SuppressWarnings("unchecked")
    private void readSQLFromFile(String fileName) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        Document document;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(is);
        } catch (DocumentException e) {
            logger.error("读取系统中用到的SQL 语句XML出错");
            throw new RuntimeException("读取sql语句XML文件出错:", e);
        }

        Element root = document.getRootElement();
        String namespace = root.attribute("namespace").getValue();
        List<Element> sqlElements = root.selectNodes("sqlElement");
        String key;
        for (Element sql : sqlElements) {
            key = namespace + "." + sql.attribute("key").getValue();
            if (sqlContainer.containsKey(key)) {
                logger.warn("key值: {} 重复", key);
            }
            sqlContainer.put(key, sql.getText());
        }

        IOUtils.closeQuietly(is);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSqlFilePaths(List<String> sqlFilePaths) {
        this.sqlFilePaths = sqlFilePaths;
    }

}
