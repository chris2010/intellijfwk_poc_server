package com.clt.base.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clt.base.dao.BaseDao;
import com.clt.base.dto.Pager;
import com.clt.base.exception.BizException;
import com.clt.base.service.BaseService;

/**
 * Created by liujinbang on 15/8/26.
 */
@Service("baseService")
public class BaseServiceImpl<T> implements BaseService<T> {
	private Class<T> entityClass;
	
    //@Autowired()
    @Resource(name="baseDaoImpl")
    private BaseDao<T> baseDAO;
    
	@Override
	public int insert(T t) throws BizException{
		return baseDAO.insert(t);
	}

	@Override
	public int update(T t) throws BizException{
		return baseDAO.update(t);
		
	}

	@Override
	public int delete(Integer id) throws BizException{
		return baseDAO.delete(entityClass,id);
		
	}

	@Override
	public T loadBySqlName(String sqlName,Object obj){
		return  (T) baseDAO.loadBySqlName(sqlName, obj);
	}

	@Override
	public T loadBySqlName(String sqlName,Map<String,Object> params){
		return  (T) baseDAO.loadBySqlName(sqlName, params);
	}

	@Override
	public List<T> list(Class<T> clz,Map<String,Object> params){
		return  baseDAO.list(clz,params);
	}

	@Override
	public List<T> listBySqlName(String sqlName,Map<String,Object> params){
		return (List<T>) baseDAO.loadBySqlName(sqlName, params);
	} 

	@Override
	public Pager<T> find(Class<T> clz,Map<String,Object> params){
		return (Pager<T>) baseDAO.find(clz, params);
	}

	@Override
	public Pager<T> find(String sqlName,Map<String,Object> params){
		return (Pager<T>) baseDAO.find(sqlName, params);
	}

}
