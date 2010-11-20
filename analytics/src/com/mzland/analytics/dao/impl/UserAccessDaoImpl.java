package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.UserAccessDao;
import com.mzland.analytics.entity.UserAccess;

@Repository
public class UserAccessDaoImpl extends SimpleHibernateDaoImpl<UserAccess, String> implements UserAccessDao{

}
