package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.GSDownDetailDao;
import com.mzland.analytics.entity.GamesoftDownDetail;

@Repository
public class GSDownDetailDaoImpl extends SimpleHibernateDaoImpl<GamesoftDownDetail, String> implements GSDownDetailDao{

}
