package com.mzland.analytics.service;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.utils.reflection.ConvertUtils;

import com.mzland.analytics.dao.DownDao;
import com.mzland.analytics.dao.DownDetailDao;
import com.mzland.analytics.dao.GSDownDetailDao;
import com.mzland.analytics.dao.GamesoftDownDao;
import com.mzland.analytics.dao.UploadDao;
import com.mzland.analytics.dao.UserAccessDao;
import com.mzland.analytics.dao.UserInfoDao;
import com.mzland.analytics.dto.GSDownDTO;
import com.mzland.analytics.dto.MMDownDTO;
import com.mzland.analytics.dto.UploadDTO;
import com.mzland.analytics.dto.UserLogDTO;
import com.mzland.analytics.entity.Down;
import com.mzland.analytics.entity.DownDetail;
import com.mzland.analytics.entity.GamesoftDown;
import com.mzland.analytics.entity.GamesoftDownDetail;
import com.mzland.analytics.entity.Upload;
import com.mzland.analytics.entity.UserAccess;
import com.mzland.analytics.entity.UserInfo;

@Service
public class StatisticsSvcImpl {

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

	@Autowired
	private GamesoftDownDao gamesoftDownDao;
	@Autowired
	private GSDownDetailDao gsDownDetailDao;

	@Transactional
	public void uploadRecord(UploadDTO uploadDTO) {
		Upload upload = ConvertUtils.beanCopy(new Upload(), uploadDTO);
		uploadDao.save(upload);
	}

	@Transactional
	public void userAccess(UserLogDTO userLogDTO) {
		UserInfo userInfo = userInfoDao.get(userLogDTO.getCustomerId());
		if (userLogDTO.getUid() != 0) {
			if (userInfo != null) {
				userInfo.setLastloginip(userLogDTO.getUserIp());
				userInfo.setLastlogintime(new Timestamp(System.currentTimeMillis()));
				userInfo.setUserImei(userLogDTO.getImei());
				userInfoDao.saveOrUpdate(userInfo);
			} else {
				userInfo = new UserInfo();
				userInfo.setUsername(userLogDTO.getUsername());
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

	@Transactional
	public int mmDown(MMDownDTO downDTO) {
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
			down.setDownloadsTotal(downs);
			down.setMultimediaId(downDTO.getId());
			down.setTitle(downDTO.getTitle());
			downDao.save(down);
		}
		DownDetail downDetail = new DownDetail();
		downDetail.setMultimediaId(downDTO.getId());
		downDetail.setCustomerId(downDTO.getCustomerId());
		downDetailDao.save(downDetail);
		return downs;
	}

	@Transactional
	public int gsDown(GSDownDTO gsDownDTO) {
		GamesoftDown down = gamesoftDownDao.get(gsDownDTO.getId());
		int downs = 1;
		if (down != null) {
			downs = down.getDownloadsTotal() + 1;
			down.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
			down.setDownloadsTotal(downs);
			gamesoftDownDao.saveOrUpdate(down);
		} else {
			down = new GamesoftDown();
			down.setCategoryId(gsDownDTO.getCategoryId());
			down.setDownloadsTotal(downs);
			down.setGamesoftId(gsDownDTO.getId());
			down.setTitle(gsDownDTO.getTitle());
			gamesoftDownDao.save(down);
		}
		GamesoftDownDetail downDetail = new GamesoftDownDetail();
		downDetail.setGamesoftId(gsDownDTO.getId());
		downDetail.setCustomerId(gsDownDTO.getCustomerId());
		downDetail.setPhoneModelId(gsDownDTO.getPhoneModelId());
		gsDownDetailDao.save(downDetail);
		return downs;
	}

}
