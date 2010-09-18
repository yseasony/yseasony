package org.yseasony.blog.service;

import java.io.Serializable;
import java.util.List;

import org.yseasony.blog.exception.MyException;


public interface BaseSvc<T, PK extends Serializable> {

	public void save(T t) throws MyException;

	public void delete(PK id);

	public void delete(T entity);

	public int getMax(String table);

	public boolean exist(String column, String value);

	public T getById(PK id);

	public List<T> getListAll();
}