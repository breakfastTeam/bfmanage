package com.bean.core.orm.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.bean.core.page.Page;

/**
 * @author Felix
 * @created 2013/11/25
 * @描述：基层业务类接口 供其他业务接口类继承
 */
@SuppressWarnings("unchecked")
public interface BaseService<T, PK extends Serializable> {

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
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void update(PK[] ids);

	/**
	 * 根据ID业务删除实体对象 将 invalidFlag修改为1
	 * 
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

//	/**
//	 * 根据hql进行分页查询
//	 * 
//	 * @param page
//	 *            Page对象
//	 * @param hql
//	 *            hql语句
//	 * @param object
//	 *            实体域对象
//	 * @return Page对象
//	 */
//	public Page findPageHql(Page<T> page, String hql, final Object object);

//	/**
//	 * 根据sql进行分页查询
//	 * 
//	 * @param page
//	 *            Page对象
//	 * @param sql
//	 *            sql语句
//	 * @param map
//	 *            实体类
//	 * @param pList
//	 *            查询条件集合
//	 * @return Page对象
//	 */
//	public Page<T> findPageSql(Page<T> page, String sql, Map<String, Class> map,
//			List<Object> pList);

	/**
	 * 获取所有实体对象集合
	 * 
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
	 * @param page
	 *            Page对象
	 * @param hql
	 *            hql语句
	 * @param object
	 *            实体域对象
	 * @return Long 结果数
	 */
	public Long getPageCountHql(String hql, Object object);

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
	public BigInteger getPageCountSql(String sql, List<Object> pList);

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
	 * 
	 * @param hql
	 *            sql语句
	 * @param entity
	 *            条件
	 * @return
	 */
	public boolean updateHQL(String hql, T entity);

	/**
	 * 修改方法 HQL语句修改
	 * 
	 * @param hql
	 *            sql语句
	 * @return
	 */
	public boolean updateHQL(String hql);

	/**
	 * 修改方法 原生态SQL语句修改
	 * 
	 * @param sql
	 *            sql语句
	 * @return
	 */
	public boolean updateSQL(String sql);
}