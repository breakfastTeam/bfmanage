package com.bean.core.orm.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.bean.core.page.Page;

/**
 * @author Felix
 * @created 2013/11/25
 * @描述：持久化接口类  供其他各模块接口类继承
 */

@SuppressWarnings("unchecked")
public abstract interface BaseDao<T, PK extends Serializable> {
	public void flush();
	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);
	
	/**
	 * 根据ID数组批量业务删除实体对象 将 invalidFlag修改为1
	 * @param ids 
	 * ID数组
	 */
	public void update(PK[] ids);
	
	/**
	 * 根据ID业务删除实体对象 将 invalidFlag修改为1
	 * @param id
	 *            记录ID
	 */
	public boolean update(PK id);
	
	/**
	 * 根据传入的map对象来修改对应字段的值
	 * 
	 * @param map
	 */
	public boolean update(Map map);
	
	public List<T> findBySql(final String sql, final Object... values);
	public List<T> find(final String hql, final Object... values);
	public List<T> find(final String hql, final List values);
	
	
	
	
	/**
	 * 获取所有实体对象集合
	 * @param hql
	 *            hql语句
	 * @param object
	 *            实体域对象
	 * @return 实体对象集合
	 */
	public List<T> getAll(String hql, final Object object);

	/**
	 * 根据hql进行分页查询
	 * 
	 * @param Page
	 *            Page对象
	 * @param hql
	 *            hql语句
	 * @param object
	 *            实体域对象
	 * @return Long 结果数
	 */
	public Long getPageCountHql(String hql, Object object);
	/**
	 * 方法描述:查询总条数 与分页(2)对应
	 * */
	public Long searchPageCountHql(String hql, List<Object> pList);
	/**
	 * 根据hql进行分页查询
	 * 
	 * @param page
	 *            Page对象
	 * @param sql
	 *            sql语句
	 * @param pList
	 *            查询条件集合
	 * @return Long 结果数
	 */
	public BigInteger searchPageCountSql(String sql, List<Object> pList);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 * @throws Exception 
	 */
	public T get(PK id) throws Exception;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * 
	 * @return ID
	 */
	public PK save(T entity);
	
	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);
	
	/**
	 * 修改方法 HQL语句修改
	 * @param hql sql语句
	 * @param entity 条件
	 * @return
	 */
	public boolean updateHQL(String hql, T entity);
	
	/**
	 * 修改方法 HQL语句修改
	 * @param hql sql语句
	 * @return
	 */
	public boolean updateHQL(String hql);
	
	/**
	 * 修改方法 原生态SQL语句修改
	 * @param sql sql语句
	 * @return
	 */
	public boolean updateSQL(String sql);
	
	
	/**
	 * 执行hql语句
	 * @param hql
	 * @return
	 */
	public int executeHql(String hql);

}
