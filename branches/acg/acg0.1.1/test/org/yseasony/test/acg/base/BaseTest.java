package org.yseasony.test.acg.base;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/applicationContext-acg.xml"})
@TransactionConfiguration(defaultRollback = false,transactionManager="transactionManager")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected MockHttpServletRequest request = new MockHttpServletRequest();
	
	protected MockHttpServletResponse response = new MockHttpServletResponse();
	
	protected MockHttpSession session = (MockHttpSession) request.getSession();

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}
