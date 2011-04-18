package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.DownDetailDao;
import com.mzland.analytics.entity.DownDetail;

@Repository
public class DownDetailDaoImpl extends SimpleHibernateDaoImpl<DownDetail, String> implements
		DownDetailDao {

}
