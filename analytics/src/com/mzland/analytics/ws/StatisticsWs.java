package com.mzland.analytics.ws;

import java.util.HashMap;

/**
 * 资源统计
 * 
 * @author yseasony(yseasony@gmail.com)
 */
public interface StatisticsWs {

	/**
	 * upload
	 * 
	 * @return
	 */
	public void uploadRecord(HashMap<String, Object> u);

	/**
	 * access index,login
	 */
	public void userAccess(HashMap<String, Object> userLog);

	/**
	 * multimedia down
	 * 
	 * @param mmDown
	 */
	public int mmDown(HashMap<String, Object> mmDown);
}
