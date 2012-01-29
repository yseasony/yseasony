package org.yseasony.utils.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class TestBase extends AbstractJUnit4SpringContextTests {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected MockHttpServletRequest request = new MockHttpServletRequest();

    protected MockHttpServletResponse response = new MockHttpServletResponse();

    protected MockHttpSession session = (MockHttpSession) request.getSession();

}
