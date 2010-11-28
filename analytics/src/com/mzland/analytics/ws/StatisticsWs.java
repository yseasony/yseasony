package com.mzland.analytics.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mzland.analytics.dto.GSDownDTO;
import com.mzland.analytics.dto.MMDownDTO;
import com.mzland.analytics.dto.UploadDTO;
import com.mzland.analytics.dto.UserLogDTO;
import com.mzland.analytics.service.StatisticsSvcImpl;

/**
 * 资源统计
 * 
 * @author yseasony(yseasony@gmail.com)
 */
@Component
@Path(value = "/analytics")
public class StatisticsWs {

	@Autowired
	private StatisticsSvcImpl statisticsSvcImpl;

	@POST
	@Path(value = "/uploadRecord")
	@Consumes(MediaType.APPLICATION_JSON)
	public void uploadRecord(UploadDTO uploadDTO) {
		statisticsSvcImpl.uploadRecord(uploadDTO);
	}
	
	@POST
	@Path(value = "/userAccess")
	@Consumes(MediaType.APPLICATION_JSON)
	public void userAccess(UserLogDTO userLogDTO) {
		statisticsSvcImpl.userAccess(userLogDTO);
	}
	
	@POST
	@Path(value = "/mmDown")
	@Consumes(MediaType.APPLICATION_JSON)
	public void mmDown(MMDownDTO downDTO) {
		statisticsSvcImpl.mmDown(downDTO);
	}
	
	@POST
	@Path(value = "/gsDown")
	@Consumes(MediaType.APPLICATION_JSON)
	public void gsDown(GSDownDTO gsDownDTO) {
		statisticsSvcImpl.gsDown(gsDownDTO);
	}
}
