package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.DownDao;
import com.mzland.analytics.entity.Down;

@Repository
public class DownDaoImpl extends SimpleHibernateDaoImpl<Down, String> implements DownDao {}
