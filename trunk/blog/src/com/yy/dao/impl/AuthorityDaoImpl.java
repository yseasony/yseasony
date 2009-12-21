package com.yy.dao.impl;

import org.springframework.stereotype.Repository;

import com.yy.dao.IAuthorityDao;
import com.yy.model.Authority;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Repository
public class AuthorityDaoImpl extends HibernateDao<Authority, Long> implements IAuthorityDao {
}
