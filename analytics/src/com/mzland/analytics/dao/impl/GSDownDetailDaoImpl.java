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
	public List<String> countWeek() {
		String sql = "SELECT gamesoft_id FROM `ma_gamesoft_down_detail` WHERE download_time BETWEEN DATE_FORMAT(NOW()-7,'%Y-%m-%d') AND download_time < NOW() GROUP BY gamesoft_id ORDER BY count(gamesoft_id) desc";
		return createSQLQuery(sql).addScalar("gamesoft_id", StandardBasicTypes.STRING).list();
	}

}
