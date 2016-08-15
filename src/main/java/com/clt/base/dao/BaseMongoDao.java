package com.clt.base.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author songjiangtao
 *@date 2015年12月15日 下午8:37:42
 * @param <T>
 */
@Repository
public interface BaseMongoDao<T> {

	void save(T t);

	void updateMulti(Query query, Update update);

	void updateInser(Query query, Update update);

	void updateFirst(Query query, Update update);

	void delete(T t);

	void deleteById(String id);

	T queryById(String id);

	T queryOne(Query query);

	List<T> queryList(Query query);

	List<T> getPage(Query query, int start, int size);

	Long getPageCount(Query query);
}
