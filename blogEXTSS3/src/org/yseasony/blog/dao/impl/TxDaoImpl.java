package org.yseasony.blog.dao.impl;

import org.springframework.stereotype.Repository;
import org.yseasony.blog.dao.TxDao;
import org.yseasony.blog.entity.Tx;


@Repository
public class TxDaoImpl extends HibernateDaoImpl<Tx, Integer> implements TxDao {

}
