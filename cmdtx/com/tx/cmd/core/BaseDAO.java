/**
 * Project: CMETPortal
 */
package com.tx.cmd.core;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p><strong>
 * Description:</strong>
 * </p>
 * <p><strong>
 * Copyright:</strong>&copy2009 Huateng
 * </p>
 * <p><strong>
 * Company:</strong>
 * </p>
 * @author Roger.li
 * @version 1.0
 */
public abstract class BaseDAO<T>  extends HibernateDaoSupport{

	private static Logger log = Logger.getLogger(BaseDAO.class);
//@Autowired 
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	protected abstract Class getReferenceClass();
	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a enerated identifier.
	 * (Or using the current value of the identifier property if the assigned generator is used.)
	 */
	public Serializable save(Object obj) {
		return getHibernateTemplate().save(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 */
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state.
	 */
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}
	
	public T get(Class refClass, Serializable key) {
		return (T)getHibernateTemplate().get(refClass, key);
	}
	/**
	 * 查询返回较少对象的list，支持带where条件
	 * @param query 如：from TblAppliBaseInfo t where t.appno='100'
	 * @return list
	 */
	public List find(String hql){
		return getHibernateTemplate().find(hql);
	}

	/**
	 * @author Zhongxin-Cao
	 * @date 2010-12-3
	 * save 方法： 根据对象类名，主键id 查询该对象，如果没有查到，返回null
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object save(Class clazz){
		return getSession().save(clazz);
	}

//	/**
//	* @author Zhongxin-Cao
//	* @date 2010-12-3
//	* update 方法： 根据对象类名，主键id 查询该对象，如果没有查到，返回null
//	* @param clazz
//	* @param id
//	* @return
//	*/
//	public Object update(Object object){
//	return this.getSession().update(object);
//	}

	/**
	 * @author Zhongxin-Cao
	 * @date 2010-12-3
	 * get 方法： 根据对象类名，主键id 查询该对象，如果没有查到，返回null
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object get(Class clazz, Integer id){
		return getSession().get(clazz, id);
	}

	/**
	 * @author Zhongxin-Cao
	 * @date 2010-12-3
	 * load 方法： 根据对象类名，主键id 查询该对象，如果没有查到，load()方法可能抛出无法恢复的异常(unrecoverable exception)。
	 * load();
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object load(Class clazz, Integer id){
		return getSession().load(clazz, id);
	}

	/**
	 * @author Zhongxin-Cao
	 * @date 2010-12-3
	 * getSession 方法： 返回session。
	 * getSession();
	 * @return
	 */
//	public Session getSession(){
//	return getHibernateTemplate().getSessionFactory().openSession();
//	}

	/**
	 * 根据原生sql语句查询
	 * @param query 如：select * from dual
	 * @return list
	 */
	public Object findByNativeSql(String sql){
		return getSession().createSQLQuery(sql);
	}

	/**
	 * 查询返回 列表的第一个对象
	 * @param query 如：from TblAppliBaseInfo t where t.appno='100'
	 * @return list
	 */
	public Object findUnique(String hql){
		if(getHibernateTemplate().find(hql).size()>0)
			return getHibernateTemplate().find(hql).get(0);
		return null;
	}

	/**
	 * 根据PO名字查询返回较少对象的list，不支持带where条件
	 * @param query 如：TblAppliBaseInfo
	 * @return list
	 */
	public List findByPOName(String poName){
		return getHibernateTemplate().find("from "+poName+" t");
	}

	/**
	 * findByProperty方法 根据单属性查询PO实例，返回list
	 * @author jimmy.peng
	 * @date 2010-12-8
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public List findByProperty(String poName, String propertyName, Object propertyValue) {
		log.debug("finding " + poName + " instance with property: "
				+ propertyName + ", value: " + propertyValue);
		try {
			String queryString = "from " + poName + " po where po."
			+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, propertyValue);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * findByProperty方法 根据单属性查询PO实例，
	 * @author jimmy.peng
	 * @date 2010-12-3
	 * @param propertyName
	 * @param propertyValue
	 * @param errorMsg
	 * @return
	 * @throws Exception
	 */
	public List findByPropertyException(String poName, String propertyName, Object propertyValue, String errorMsg)
	throws Exception {
		List resultLst = findByProperty(poName, propertyName, propertyValue);
		if (resultLst == null || resultLst.size() <= 0)
			 throw new Exception("没有数据");

		return resultLst;
	}

	/**
	 * queryByCondition 方法 根据对象多个属性值查询 po对象list列表,查询不到抛异常
	 * @author jimmy.peng
	 * @date 2010-12-3
	 * @param poName
	 * @param poMap
	 * @param errorMsg
	 * @return
	 * @throws Exception
	 */
	public List findByConditionException(String poName, Map poMap, String errorMsg) throws Exception {
		List resultLst = findByCondition(poName, poMap);

		if (resultLst == null || resultLst.size() <= 0)
			 throw new Exception("没有数据");

		return resultLst;
	}

	/**
	 * queryByCondition 方法 根据对象多个属性值查询 po对象list列表
	 * @author linjunyun
	 * @date 2010-11-29
	 * @param poName  po名字
	 * @param poMap  po属性名，po属性值的map对象
	 * @return
	 * @throws Exception
	 */
	public List findByCondition(String poName, Map poMap) throws Exception {
		//getHibernateTemplate().setCacheQueries(true);
		try {
			StringBuffer whereString = new StringBuffer();
			String objStr = "";
			for (Iterator iterator = poMap.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String obj = (String) poMap.get(name);
				if (whereString.length() == 0) {
					whereString.append(" where po.").append(name).append("=? ");
					objStr = objStr + obj;
				} else {
					whereString.append(" and po.").append(name).append("=? ");
					objStr = objStr + "," + obj;
				}
			}
			if (whereString.length() == 0) {
				List list = getHibernateTemplate().find(
						"from " + poName + " po ");
				return list;
			}
			String[] objArray = objStr.split(",");
			List list = getHibernateTemplate().find(
					"from " + poName + " po " + whereString.toString(), objArray);

			return list;
		} catch (Exception e) {
			log.error("queryByCondition(String, Map)", e);
			 throw new Exception("没有数据");
		}
	}

}
