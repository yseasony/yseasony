package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.UserInfoDao;
import com.mzland.analytics.entity.UserInfo;

@Repository
public class UserInfoDaoImpl extends SimpleHibernateDaoImpl<UserInfo, String> implements UserInfoDao{

}
