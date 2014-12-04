package com.bean.core.orm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bean.core.orm.dao.BaseDao;
import com.bean.core.page.Page;
import com.bean.core.utils.IReflectionUtil;


/**
 * @author Felix
 * @created 2013/11/25
 * @描述：持久化实现类 供其他各模块实现类继承
 */

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> entityClass;
	@Autowired
	protected SessionFactory sessionFactory;
	protected AbstractEntityPersister classMetadata;
	
	public void flush()
	{
		this.getSession().flush();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}


	public BaseDaoImpl() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = (T) getSession().load(entityClass, id);
		getSession().delete(entity);
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = (T) getSession().load(entityClass, id);
			getSession().delete(entity);
		}
	}

	/**
	 * 按sql查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	 public List<T> findBySql(final String sql, final Object... values) {

		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public List<T> find(final String hql, final Object... values) {

		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}
	/**
	* 设置分页参数到Query对象,辅助函数.
	*/
	protected Query setPageParameter(final Query q, final Page<T> page) {
		//hibernate的firstResult的序号从0开始
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}
	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public List<T> find(final String hql, final List values) {

		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query.list();
	}



	public List<T> getAll(String hql, final Object object) {
		List<T> list = null;
		Query query = getSession().createQuery(hql);
		if (object != null) {
			query.setProperties(object);
		}
		list = query.list();
		return list;
	}

	/**
	 * 方法描述:查询总条数 与分页(1)对应
	 */
	public Long getPageCountHql(String hql, Object object) {
		Query query = getSession().createQuery(hql);
		if (object != null) {
			query.setProperties(object);
		}
		return (Long) query.uniqueResult();
	}

	/**
	 * 方法描述:查询总条数 与分页(2)对应
	 */
	public Long searchPageCountHql(String hql, List<Object> pList) {
		Query query = getSession().createQuery(hql);
		if (pList != null) {
			for (int i = 0; i < pList.size(); i++) {
				query.setParameter(i, pList.get(i));
			}
		}
		return (Long) query.uniqueResult();
	}

	/**
	 * 方法描述:查询总条数 与分页(3)对应
	 */
	public BigInteger searchPageCountSql(String sql, List<Object> pList) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (pList != null) {
			for (int i = 0; i < pList.size(); i++) {
				query.setParameter(i, pList.get(i));
			}
		}
		return (BigInteger) query.uniqueResult();
	}

	public T get(PK id) throws Exception {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	public PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 修改方法 HQL语句修改
	 * 
	 * @param hql
	 *            sql语句
	 * @param entity
	 *            条件
	 * @return
	 */
	public boolean updateHQL(String hql, T entity) {
		Query query = getSession().createQuery(hql);
		query.setProperties(entity);
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 修改方法 HQL语句修改
	 * 
	 * @param hql
	 *            sql语句
	 * @return
	 */
	public boolean updateHQL(String hql) {
		Query query = getSession().createQuery(hql);
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 修改方法 原生态SQL语句修改
	 * 
	 * @param sql
	 *            sql语句
	 * @return
	 */
	public boolean updateSQL(String sql) {
		Query query = getSession().createSQLQuery(sql);
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}


	public void update(PK[] ids) {
		// TODO Auto-generated method stub
		if (ids != null) {
			for (PK id : ids) {
				update(id);
			}
		}
	}

	public boolean update(PK id) {
		// TODO Auto-generated method stub
		if (id != null) {
			classMetadata = (AbstractEntityPersister) sessionFactory
					.getClassMetadata(entityClass);
			return updateHQL("update " + classMetadata.getEntityName()
					+ " set invalidFlag=1 where "
					+ classMetadata.getIdentifierPropertyName() + "='"
					+ id.toString() + "'");
		}
		return false;
	}

	public boolean update(Map map) {
		// TODO Auto-generated method stub
		classMetadata = (AbstractEntityPersister) sessionFactory
		.getClassMetadata(entityClass);
		String hql = "";
		if (map != null) {
			hql = "update " + classMetadata.getEntityName() 
			+ " set "+map.get("name")+"="+map.get("value")+" where "
			+ classMetadata.getIdentifierPropertyName() + "='"
			+ map.get("pk").toString() + "'";
			return updateHQL(hql);
		}
		return false;
	}
	
	
	public int executeHql(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}
	/**
	 * 按hql分页查询.
	 * 
	 * @param page 分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *             其中autoCount指定是否动态获取总结果数.
	 * @param params 数量可变的String.
	 * @since 2014-04-26
	 *  变更记录：
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 * 变更记录：
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findByHql(final Page<T> page, String hql, final Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		page.setTotalCount(query.list().size());
		if (page.getPageNo() != -1) {// -1表示不分页全部查出
			query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}
		List list = query.list();
		page.setResult(list);
		return page;
	}
	/**
	 * 按hql分页查询.
	 * 
	 * @param page 分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *             其中autoCount指定是否动态获取总结果数.
	 * @param params 数量可变的String.
	 * @since 2014-04-26
	 *  变更记录：
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 * 变更记录：
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findByHql(final Page<T> page, String hql, final List<Object> params) {
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		page.setTotalCount(query.list().size());
		if (page.getPageNo() != -1) {// -1表示不分页全部查出
			query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}
		List list = query.list();
		page.setResult(list);
		return page;
	}
	
	/**
	 * 按hql分页查询.
	 * 
	 * @param page 分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *             其中autoCount指定是否动态获取总结果数.
	 * @param params 数量可变的String.
	 * @since 2014-04-26
	 *  变更记录：
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 * 变更记录：
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findBySql(final Page<T> page, String sql, final Object... params) {
		Query query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		page.setTotalCount(query.list().size());
		if (page.getPageNo() != -1) {// -1表示不分页全部查出
			query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}
		List list = query.list();
		page.setResult(list);
		return page;
	}
	/**
	 * 按hql分页查询.
	 * 
	 * @param page 分页参数.支持pageSize、firstResult和orderBy、order、autoCount参数.
	 *             其中autoCount指定是否动态获取总结果数.
	 * @param params 数量可变的String.
	 * @since 2014-04-26
	 *  变更记录：
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 * 变更记录：
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findBySql(final Page<T> page, String sql, final List<String> params) {
		Query query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		
		page.setTotalCount(query.list().size());
		
		if (page.getPageNo() != -1) {// -1表示不分页全部查出
			query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
			query.setMaxResults(page.getPageSize());
		}
		
		List list = query.list();
		page.setResult(list);
		return page;
	}
	

	/**
	 * 根据Criterion条件创建Criteria.
	 *
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @author chy
	 * @param criterions 数量可变的Criterion.
	 *  @since 2014-04-26
	 *  变更记录：
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @author chy
	 * @param  cris 数量可变的Criterion.
	 *  @since 2014-04-26
	 *  变更记录：
	 */
	public Criteria createCriteria(final List<Criterion> cris) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : cris) {
			criteria.add(c);
		}
		return criteria;
	}


	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 * @author chy
	 * @param c,page
	 * @return
	 * @since 2014-04-26
	 * 变更记录：
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		//hibernate的firstResult的序号从0开始
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "different");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString, final Map<String, Object> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (Entry<String, Object> entry : values.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createSQLQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public Query createSQLQuery(final String queryString, final Map<String, Object> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (Entry<String, Object> entry : values.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
}
