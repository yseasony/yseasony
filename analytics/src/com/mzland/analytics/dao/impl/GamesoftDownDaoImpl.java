package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.GamesoftDownDao;
import com.mzland.analytics.entity.GamesoftDown;

@Repository
public class GamesoftDownDaoImpl extends SimpleHibernateDaoImpl<GamesoftDown, String> implements GamesoftDownDao{

}
