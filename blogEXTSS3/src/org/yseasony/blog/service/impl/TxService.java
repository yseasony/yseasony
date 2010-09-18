package org.yseasony.blog.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yseasony.blog.dao.HibernateDao;
import org.yseasony.blog.entity.Tx;

@Service
public class TxService extends BaseSvcImpl<Tx, Integer>{

	private static final Logger log = Logger.getLogger(TxService.class);
	
	@Transactional
	public void t() {
		log.debug("save it");
		int i = 1;
		save(new Tx("a"));
		save(new Tx("b"));
		save(new Tx("c"));
		save(new Tx("d"));
		if (i==0) {
			throw new RuntimeException("ts");
		}
		save(new Tx("e"));
	}
	
	@Resource(name = "txDaoImpl")
	public void setHibernateDao(HibernateDao<Tx, Integer> hibernateDao) {
		super.setHibernateDao(hibernateDao);
	}
}
