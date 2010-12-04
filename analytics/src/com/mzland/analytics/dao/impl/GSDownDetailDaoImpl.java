package com.mzland.analytics.dao.impl;

import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.GSDownDetailDao;
import com.mzland.analytics.entity.GamesoftDownDetail;

@Repository
public class GSDownDetailDaoImpl extends SimpleHibernateDaoImpl<GamesoftDownDetail, String> implements GSDownDetailDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> countWeek(int modelId,int categoryId,int limit) {
		String sql = "SELECT gdd.gamesoft_id FROM ma_gamesoft_down_detail gdd INNER JOIN ma_gamesoft_down gd ON gdd.gamesoft_id = gd.gamesoft_id WHERE gdd.phone_model_id = "+modelId+" AND gd.category_id = "+categoryId+" AND gdd.download_time BETWEEN DATE_FORMAT(NOW()-7,'%Y-%m-%d') AND NOW() GROUP BY gdd.gamesoft_id ORDER BY count(gdd.gamesoft_id) desc LIMIT "+limit+"";
		return createSQLQuery(sql).addScalar("gamesoft_id", StandardBasicTypes.STRING).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> countDay(int modelId,int categoryId,int limit) {
		String sql = "SELECT gdd.gamesoft_id FROM ma_gamesoft_down_detail gdd INNER JOIN ma_gamesoft_down gd ON gdd.gamesoft_id = gd.gamesoft_id WHERE gdd.phone_model_id = "+modelId+" AND gd.category_id = "+categoryId+" AND gdd.download_time BETWEEN DATE_FORMAT(NOW()-1,'%Y-%m-%d') AND NOW() GROUP BY gdd.gamesoft_id ORDER BY count(gdd.gamesoft_id) desc LIMIT "+limit+"";
		return createSQLQuery(sql).addScalar("gamesoft_id", StandardBasicTypes.STRING).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> countMonth(int modelId,int categoryId,int limit) {
		String sql = "SELECT gdd.gamesoft_id FROM ma_gamesoft_down_detail gdd INNER JOIN ma_gamesoft_down gd ON gdd.gamesoft_id = gd.gamesoft_id WHERE gdd.phone_model_id = "+modelId+" AND gd.category_id = "+categoryId+" AND DATE_FORMAT(gdd.download_time,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') GROUP BY gdd.gamesoft_id ORDER BY count(gdd.gamesoft_id) desc LIMIT "+limit+"";
		return createSQLQuery(sql).addScalar("gamesoft_id", StandardBasicTypes.STRING).list();
	}
}
