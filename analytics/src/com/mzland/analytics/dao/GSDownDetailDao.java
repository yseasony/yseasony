package com.mzland.analytics.dao;

import java.util.List;

import com.mzland.analytics.entity.GamesoftDownDetail;

public interface GSDownDetailDao extends SimpleHibernateDao<GamesoftDownDetail, String>{

	List<String> countWeek(int modelId,int categoryId,int limit);

	List<String> countDay(int modelId, int categoryId, int limit);

	List<String> countMonth(int modelId, int categoryId, int limit);
}
