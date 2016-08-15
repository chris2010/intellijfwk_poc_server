package com.clt.base.service;

import java.util.List;
import java.util.Map;

import com.clt.base.dto.Pager;
import com.clt.base.exception.BizException;

/**
 * Created by liujinbang on 15/8/26.
 */
public interface BaseService<T> {
    int  insert(T t) throws BizException;
    int  update(T t) throws BizException;
    int  delete(Integer id) throws BizException;
	T loadBySqlName(String sqlName,Object obj); 
	T loadBySqlName(String sqlName,Map<String,Object> params);
	List<T> list(Class<T> clz,Map<String,Object> params); 
	List<T> listBySqlName(String sqlName,Map<String,Object> params); 
	Pager<T> find(Class<T> clz,Map<String,Object> params); 
	Pager<T> find(String sqlName,Map<String,Object> params);
}
