package com.mzland.analytics.ws;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mzland.analytics.service.CooperationSvcImpl;

@Component
@Path(value = "/phone")
public class PhoneWs extends BaseWs {

	@Autowired
	private CooperationSvcImpl cooperationSvcImpl;

	@Path("/cooperationRecord")
	public String recordLog(@QueryParam(value = "pageNo") String imei,
							@QueryParam(value = "version") String version,
							@QueryParam(value = "pkgNumber") String pkgNumber) {
		Integer result = cooperationSvcImpl.recordLog(imei, version, pkgNumber);
		return result.toString();
	}
}
