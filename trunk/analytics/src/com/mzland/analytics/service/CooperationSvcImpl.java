package com.mzland.analytics.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzland.analytics.dao.CooperationLogDao;
import com.mzland.analytics.dao.PromotionPkgDao;
import com.mzland.analytics.entity.CooperationLog;
import com.mzland.analytics.entity.PromotionPkg;

@Service
public class CooperationSvcImpl {

	private final static String DEFAULT_PKG_NUMBER = "00000";
	private static Logger logger = Logger.getLogger(CooperationSvcImpl.class);

	@Autowired
	private CooperationLogDao cooperationLogDao;
	@Autowired
	private PromotionPkgDao promotionPkgDao;

	/**
	 * 
	 * @param imei
	 * @param version
	 * @param pkgNumber
	 * @return 
	 * -1 已经存在此用户记录
	 * -3 保存时出现错误 
	 * 1 保存成功
	 */
	@Transactional
	public int recordLog(String imei, String version, String pkgNumber) {
		CooperationLog cooperationLog = cooperationLogDao.get(imei);
		if (cooperationLog != null) {
			return -1;
		}
		
		PromotionPkg promotionPkg = promotionPkgDao.get(pkgNumber);
		if (promotionPkg == null) {
			pkgNumber = DEFAULT_PKG_NUMBER;
		}
		
		cooperationLog = new CooperationLog(imei, version, pkgNumber);
		try {
			cooperationLogDao.save(cooperationLog);
			return 1;
		}
		catch (Exception e) {
			logger.error("record cooperationLog error!", e);
			return -3;
		}

	}
}
