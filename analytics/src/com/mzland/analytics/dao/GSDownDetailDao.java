package com.mzland.analytics.dao;

import java.util.List;

import com.mzland.analytics.entity.GamesoftDownDetail;

public interface GSDownDetailDao extends SimpleHibernateDao<GamesoftDownDetail, String>{

	public List<String> countWeek();
}
