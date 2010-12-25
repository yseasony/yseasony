package com.mzland.analytics.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mzland.analytics.service.CooperationSvcImpl;

@Component
@Path(value = "/phone")
public class PhoneWs extends BaseWs {

	@Autowired
	private CooperationSvcImpl cooperationSvcImpl;

	@GET
	@Path("/cooperationRecord")
	@Produces(MediaType.TEXT_PLAIN)
	public String recordLog(@QueryParam(value = "imei") String imei,
							@QueryParam(value = "version") String version,
							@QueryParam(value = "pkgNumber") String pkgNumber) {
		if (StringUtils.isBlank(imei)) {
			return "-4";
		}
		if (StringUtils.isBlank(version)) {
			return "-5";
		}
		if (StringUtils.isBlank(pkgNumber)) {
			return "-6";
		}
		Integer result = cooperationSvcImpl.recordLog(imei, version, pkgNumber);
		return result.toString();
	}
}
