package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.PromotionPkgDao;
import com.mzland.analytics.entity.PromotionPkg;

@Repository
public class PromotionPkgDaoImpl extends SimpleHibernateDaoImpl<PromotionPkg, String> implements PromotionPkgDao{

}
