package com.clt.base.dao;

import java.util.List;
import java.util.Map;

import com.clt.base.dto.Pager;
import com.clt.base.exception.BizException;

/**
 * Created by songjt20151213
 */
public interface BaseDao<T> {

	int insert(T obj) throws BizException;
	
	int update(T obj)throws BizException; 
	
	int delete(Class<T> clz,int id)throws BizException; 
	
	@SuppressWarnings("unchecked")
	T load(Class<T> clz,int id);

	@SuppressWarnings("unchecked")
	T loadBySqlName(String sqlName,Object obj); 

	@SuppressWarnings("unchecked")
	T loadBySqlName(String sqlName,Map<String,Object> params);
	
	List<T> list(Class<T> clz,Map<String,Object> params); 
	
	List<T> listBySqlName(String sqlName,Map<String,Object> params); 

	Pager<T> find(Class<T> clz,Map<String,Object> params); 
	
	Pager<T> find(String sqlName,Map<String,Object> params);
}
