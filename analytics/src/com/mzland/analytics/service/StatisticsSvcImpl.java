package com.mzland.analytics.service;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.utils.encode.JsonBinder;
import org.yseasony.utils.reflection.ConvertUtils;

import com.mzland.analytics.dao.DownDao;
import com.mzland.analytics.dao.DownDetailDao;
import com.mzland.analytics.dao.UploadDao;
import com.mzland.analytics.dao.UserAccessDao;
import com.mzland.analytics.dao.UserInfoDao;
import com.mzland.analytics.dto.MMDownDTO;
import com.mzland.analytics.dto.UploadDTO;
import com.mzland.analytics.dto.UserLogDTO;
import com.mzland.analytics.entity.Down;
import com.mzland.analytics.entity.DownDetail;
import com.mzland.analytics.entity.Upload;
import com.mzland.analytics.entity.UserAccess;
import com.mzland.analytics.entity.UserInfo;
import com.mzland.analytics.ws.StatisticsWs;

@Service
public class StatisticsSvcImpl implements StatisticsWs {

	@Autowired
	private UploadDao uploadDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserAccessDao userAccessDao;

	@Autowired
	private DownDao downDao;
	
	@Autowired
	private DownDetailDao downDetailDao;

	@Transactional
	public void uploadRecord(String u) {
		UploadDTO uploadDTO = JsonBinder.buildNonDefaultBinder().fromJson(u, UploadDTO.class);
		Upload upload = ConvertUtils.beanCopy(new Upload(), uploadDTO);
		uploadDao.save(upload);
	}

	@Override
	@Transactional
	public void userAccess(HashMap<String, Object> userLog) {
		UserLogDTO userLogDTO = ConvertUtils.toObject(new UserLogDTO(), userLog);
		UserInfo userInfo = userInfoDao.get(userLogDTO.getCustomerId());
		if (userLogDTO.getUid() != 0) {
			if (userInfo != null) {
				userInfo.setLastloginip(userLogDTO.getUserIp());
				userInfo.setLastlogintime(new Timestamp(System.currentTimeMillis()));
				userInfo.setUserImei(userLogDTO.getImei());
				userInfoDao.saveOrUpdate(userInfo);
			} else {
				userInfo = new UserInfo();
				userInfo.setCustomerId(userLogDTO.getCustomerId());
				userInfo.setLastloginip(userLogDTO.getUserIp());
				userInfo.setUid(userLogDTO.getUid());
				userInfo.setUserImei(userLogDTO.getImei());
				userInfoDao.save(userInfo);
			}
		}

		if (StringUtils.isBlank(userLogDTO.getCustomerId())) {
			userLogDTO.setCustomerId("-1");
		}

		if (userLogDTO.getItemId() != 0) {
			UserAccess userAccess = new UserAccess();
			userAccess.setItemId((short) userLogDTO.getItemId());
			userAccess.setUserImei(userLogDTO.getImei());
			userAccess.setUserIp(userLogDTO.getUserIp());
			userAccess.setVersion(userLogDTO.getVersion());
			userAccess.setCustomerId(userLogDTO.getCustomerId());
			userAccessDao.save(userAccess);
		}

	}

	@Override
	@Transactional
	public int mmDown(String mmDown) {
		MMDownDTO downDTO = JsonBinder.buildNonDefaultBinder().fromJson(mmDown, MMDownDTO.class);
		Down down = downDao.get(downDTO.getId());
		int downs = 1;
		if (down != null) {
			downs = down.getDownloadsTotal() + 1;
			down.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
			down.setDownloadsTotal(downs);
			downDao.saveOrUpdate(down);
		} else {
			down = new Down();
			down.setCategoryId(downDTO.getCategoryId());
			down.setCreateTime(new Timestamp(System.currentTimeMillis()));
			down.setDownloadsTotal(downs);
			down.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
			down.setMultimediaId(downDTO.getId());
			down.setTitile(downDTO.getTitle());
		}
		DownDetail downDetail = new DownDetail();
		downDetail.setDown(down);
		downDetail.setDownloadTime(new Timestamp(System.currentTimeMillis()));
		downDetail.setCustomerId(downDTO.getCustomerId());
		downDetailDao.save(downDetail);
		return downs;
	}

}
