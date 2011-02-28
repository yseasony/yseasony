package com.mzland.analytics.ws;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yseasony.utils.encode.EncodeUtils;

import com.mzland.analytics.service.CooperationSvcImpl;

@Component
@Path(value = "/phone")
public class PhoneWs extends BaseWs {

	@Autowired
	private CooperationSvcImpl cooperationSvcImpl;

	@GET
	@Path("/cooperationRecord")
	@Produces(MediaType.TEXT_PLAIN)
	public String recordLog(@QueryParam(value = "key") String key) {
		try {
			byte[] src = EncodeUtils.hexDecode(key);
			key = Encrypt.decryptByTea(src).trim();
		}
		catch (Exception e) {
			logger.error("", e);
			return "-2";
		}

		HashMap<String, String> map = new HashMap<String, String>();
		String[] parameters = key.split("&");
		for (int i = 0; i < parameters.length; i++) {
			String[] keyValues = parameters[i].split("=");
			try {
				map.put(keyValues[0], keyValues[1]);
			}
			catch (ArrayIndexOutOfBoundsException e) {
				map.put(keyValues[0], "");
			}
		}
		String imei = map.get("imei");
		if (StringUtils.isBlank(map.get("imei"))) {
			return "-4";
		}
		String version = map.get("version");
		if (StringUtils.isBlank(map.get("version"))) {
			return "-5";
		}
		String pkgNumber = map.get("pkgNumber");
		if (StringUtils.isBlank(map.get("pkgNumber"))) {
			return "-6";
		}
		Integer result = cooperationSvcImpl.recordLog(imei, version, pkgNumber);
		return result.toString();
	}

	public static void main(String[] args) {
		String key = "4ECDC328ED294E9EFA4A825DAC383E56AC335C4E30BB10AD45C4A04F1528E4BC061F207A6ABFDDF530D30EBF7F740FB59DCCA96045093C6A";
		byte[] src = EncodeUtils.hexDecode(key);
		key = Encrypt.decryptByTea(src).trim();
		
		System.out.println(key);
	}
}
