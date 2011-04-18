package com.mzland.analytics.dao.impl;

import org.springframework.stereotype.Repository;

import com.mzland.analytics.dao.UploadDao;
import com.mzland.analytics.entity.Upload;

@Repository
public class UploadDaoImpl extends SimpleHibernateDaoImpl<Upload, String> implements UploadDao{

}
