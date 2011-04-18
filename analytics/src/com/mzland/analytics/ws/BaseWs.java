package com.mzland.analytics.ws;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

public class BaseWs {
	protected Logger logger = Logger.getLogger(getClass());
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";
	
	/**
	 * 标准状态码与自定义信息.
	 */
	protected WebApplicationException buildException(Status status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type("text/plain").build());
	}

	/**
	 * 自定义状态码与自定义信息.
	 */
	protected WebApplicationException buildException(int status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type("text/plain").build());
	}
	
}
