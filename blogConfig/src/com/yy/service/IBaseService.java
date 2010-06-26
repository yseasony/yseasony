package com.yy.service;

import java.io.Serializable;
import java.util.List;

import com.yy.exception.MyException;

public interface IBaseService<T, PK extends Serializable> {

	public void save(T t) throws MyException;

	public void delete(PK id);

	public void delete(T entity);

	public int getMax(String table);

	public T exist(String column, String value);

	public T getById(PK id);

	public List<T> getListAll();
}