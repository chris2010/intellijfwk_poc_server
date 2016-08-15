package com.clt.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clt.base.dto.Pager;
import com.clt.base.dto.SystemContext;
import com.clt.base.exception.BizException;

/**
 * Created by songjt on 15/12/12.
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T>{
	//注入SqlSessionTemplate实例
	@Resource(name="sqlSessionTemplate")
    private SqlSession sqlSession;
	
	/**
	 * 获得当前事物的session
	 */
	public SqlSession getSqlSession(){
		return this.sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {       
	    this.sqlSession = sqlSession;      
    } 		
	
	public int insert(T obj) throws BizException {
		try {
			int result = sqlSession.insert(obj.getClass().getName()+".insert",obj);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} 
	}
	
	public int update(T obj) throws BizException{
		try {
			int result = sqlSession.update(obj.getClass().getName()+".update", obj);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int delete(Class<T> clz,int id) throws BizException{
		try {
			int result = sqlSession.delete(clz.getName()+".delete", id);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T load(Class<T> clz,int id) {
		System.out.println("函数执行：BaseDaoImpl.load");
		T t = null;
		t = (T)getSqlSession().selectOne(clz.getName()+".load",id);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public T loadBySqlName(String sqlName,Map<String,Object> params) {
		return (T)sqlSession.selectOne(sqlName,params);
	}
	
	@SuppressWarnings("unchecked")
	public T loadBySqlName(String sqlName,Object obj) {
		return (T)sqlSession.selectOne(sqlName,obj);
	}

	public List<T> list(Class<T> clz,Map<String,Object> params) {
		return this.listBySqlName(clz.getName()+".list", params);
	}
	
	public List<T> listBySqlName(String sqlName,Map<String,Object> params) {
		List<T> list = null;
		list = sqlSession.selectList(sqlName,params);
		return list;
	}
	
	public Pager<T> find(Class<T> clz,Map<String,Object> params) {
		return this.find(clz.getName()+".find", params);
	}
	
	public Pager<T> find(String sqlName,Map<String,Object> params) {
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Pager<T> pages = new Pager<T>();
	//	SqlSession session = null;
		if(params==null) params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		params.put("sort", sort);
		params.put("order", order);
		List<T> datas = sqlSession.selectList(sqlName, params);
		pages.setDatas(datas);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		int totalRecord = sqlSession.selectOne(sqlName+"_count",params);
		pages.setTotalRecord(totalRecord);
		return pages;
	}
	

}
