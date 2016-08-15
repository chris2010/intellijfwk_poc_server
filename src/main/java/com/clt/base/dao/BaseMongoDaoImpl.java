package com.clt.base.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
/**
 * 
 * @author songjiangtao
 *@date 2015年12月15日 下午8:38:03
 * @param <T>
 */
@Repository
public abstract class BaseMongoDaoImpl<T>  implements BaseMongoDao<T>{
	private static final Logger log = Logger.getLogger(BaseMongoDaoImpl.class);
	@Autowired
	public MongoTemplate mongoTemplate;
	
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }	
	/**
     * 保存一个对象
     * @param t
     * @return
     */
	@Override
    public void save(T t){
        log.info("[Mongo Dao ]save:" + t);
        this.mongoTemplate.save(t);
    }
    
    /**
     * 根据Id从Collection中查询对象
     * @param id   实体对象的Id,对应Collection中记录的_id字段. 
     *             需要说明的是,Mongdo自身没有主键自增机制.解决方法
     *             实体入库的时候,程序中为实体赋主键值.
     *             实体入库的时候,在mongodb中自定义函数实现主键自增机制.定义方法同js代码类似
     * @return
     */
	@Override
    public T queryById(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        log.info("[Mongo Dao ]queryById:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * 根据条件查询集合
     * @param query  查询条件      
     * @return       满足条件的集合
     */
	@Override
    public List<T> queryList(Query query){
        log.info("[Mongo Dao ]queryList:" + query);
        return this.mongoTemplate.find(query, this.getEntityClass());
    }
    /**
     * 通过条件查询单个实体
     * @param query
     * @return
     */
	@Override
    public T queryOne(Query query){
        log.info("[Mongo Dao ]queryOne:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }
    /**
     * 分页查询
     * 查询单个用的是mongoTemplate.findOne方法,查询多条的用的是mongoTemplate.find
     * @param query  查询条件
     * @param start  查询起始值  类似mysql查询中的 limit start, size 中的 start</strong>
     * @param size   查询大小     类似mysql查询中的 limit start, size 中的 size</strong>
     * @return       满足条件的集合
     */
	@Override
    public List<T> getPage(Query query, int start, int size){
        query.skip(start);
        query.limit(size);
        log.info("[Mongo Dao ]queryPage:" + query + "(" + start +"," + size +")");
        List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
        return lists;
    }
    
    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     * @param query  查询条件
     * @return       满足条件的记录总数
     */
	@Override
    public Long getPageCount(Query query){
        log.info("[Mongo Dao ]queryPageCount:" + query);
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 根据Id删除用户
     * @param id
     */
	@Override
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("_id").in(id);
        if(null!=criteria){
            Query query = new Query(criteria);
            log.info("[Mongo Dao ]deleteById:" + query);
            if(null!=query && this.queryOne(query)!=null){
            	
                //sjt this.delete(query);
            }
        }
    }
    /**
     * 删除对象
     * @param t
     */
	@Override
    public void delete(T t){
        log.info("[Mongo Dao ]delete:" + t);
        this.mongoTemplate.remove(t);
    }


    /**
     * 修改操作:更新满足条件的第一个记录
     *说明:Mongodb的修改操作大致有3中.mongoTemplate.updateFirst操作、mongoTemplate.updateMulti操作、this.mongoTemplate.upsert操作.
	 *分别表示修改第一条、修改符合条件的所有、修改时如果不存在则添加.
	 *修改满足条件的第一条记录                
     * @param query
     * @param update
     */
	@Override
    public void updateFirst(Query query,Update update){
        log.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }
    /**
     * 更新满足条件的所有记录
     * @param query
     * @param update
     */
	@Override
    public void updateMulti(Query query, Update update){
        log.info("[Mongo Dao ]updateMulti:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }


    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param query
     * @param update
     */
	@Override
    public void updateInser(Query query, Update update){
        log.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.upsert(query, update, this.getEntityClass());
    }


    /**
     * 钩子方法,由子类实现返回反射对象的类型
     *上面的操作是Mongodb的基础操作封装,利用泛型实现的抽象类MongoGenDao.java,泛型中定义钩子方法,然后Dao类继承抽象类,实现该钩子方法,返回反射的类型.钩子方法的定义如下:
     * @return
     */
    //protected abstract Class<T> getEntityClass();
    protected abstract Class<T> getEntityClass();
	

    //sjt 20151216//////////////////////////////////////
    public List<T> getAllObjects() {
        return mongoTemplate.findAll(this.getEntityClass());
    }
    public void saveObject(T T) {
        mongoTemplate.insert(T);
    }
 
    public T getObject(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
                this.getEntityClass());
    }
 
    public WriteResult updateObject(String id, String name) {
        return mongoTemplate.updateFirst(
                new Query(Criteria.where("id").is(id)),
                Update.update("name", name), this.getEntityClass());
    }
 
    public void deleteObject(String id) {
        mongoTemplate
                .remove(new Query(Criteria.where("id").is(id)), this.getEntityClass());
    }
 
    public void createCollection() {
        if (!mongoTemplate.collectionExists(this.getEntityClass())) {
            mongoTemplate.createCollection(this.getEntityClass());
        }
    }
 
    public void dropCollection() {
        if (mongoTemplate.collectionExists(this.getEntityClass())) {
            mongoTemplate.dropCollection(this.getEntityClass());
        }    
    }    
}
