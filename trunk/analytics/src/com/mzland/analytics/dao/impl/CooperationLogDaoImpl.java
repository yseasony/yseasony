package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.CooperationLogDao;
import com.mzland.analytics.entity.CooperationLog;

@Repository
public class CooperationLogDaoImpl extends SimpleHibernateDaoImpl<CooperationLog, String> implements CooperationLogDao{

}
