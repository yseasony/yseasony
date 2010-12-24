package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.CooperationDao;
import com.mzland.analytics.entity.Cooperation;

@Repository
public class CooperationDaoImpl extends SimpleHibernateDaoImpl<Cooperation, Short> implements CooperationDao{

}
