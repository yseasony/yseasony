package com.mzland.analytics.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.springframework.stereotype.Component;
import org.yseasony.utils.log.Log;
import org.yseasony.utils.log.Logs;

@Component
public class TaskClientImpl {

	private static Log logger = Logs.getLog(TaskClientImpl.class);

	private WebResource client;

	public void setBaseUrl(String baseUrl) {
		logger.debug("baseUrl : " + baseUrl);
		Client jerseyClient = Client.create(new DefaultClientConfig());
		jerseyClient.setConnectTimeout(3000);
		client = jerseyClient.resource(baseUrl);
	}
	
	public void taskUpdate() {
		logger.info("taskUpdate start");
		client.path("/task/countUpdate").post();
		logger.info("taskUpdate end");
	}

}
