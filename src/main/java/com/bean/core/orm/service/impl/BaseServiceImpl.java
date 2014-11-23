package com.bean.core.orm.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.bean.core.orm.dao.BaseDao;
import com.bean.core.orm.service.BaseService;






/**
 * @author Felix
 * @created 2013/11/25
 * @描述：基层业务实现类  供其他业务实现类继承
 */
@SuppressWarnings("unchecked")
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	private BaseDao<T, PK> baseDao;


	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void delete(PK id) {
		baseDao.delete(id);
	}

	public void delete(PK[] ids) {
		baseDao.delete(ids);
	}
	
	public List<T> getAll(String hql, final Object object){
		return baseDao.getAll(hql, object);
		
	}
	public Long getPageCountHql(String hql, Object object) {
		return baseDao.getPageCountHql(hql, object);
	}

	public BigInteger getPageCountSql(String sql, List<Object> pList) {
		return baseDao.searchPageCountSql(sql, pList);
	}
	
	public T get(PK id) throws Exception {
		return baseDao.get(id);
	}

	public T load(PK id) {
		return baseDao.load(id);
	}

	public PK save(T entity) {
		return baseDao.save(entity);
	}
	public void update(T entity) {
		baseDao.update(entity);
	}

	public boolean updateHQL(String hql, T entity) {
		// TODO Auto-generated method stub
		return baseDao.updateHQL(hql, entity);
	}

	public boolean updateSQL(String sql) {
		// TODO Auto-generated method stub
		return baseDao.updateSQL(sql);
	}

	public boolean updateHQL(String hql) {
		// TODO Auto-generated method stub
		return baseDao.updateHQL(hql);
	}

	public void update(PK[] ids) {
		// TODO Auto-generated method stub
		baseDao.update(ids);
	}

	public boolean update(PK id) {
		// TODO Auto-generated method stub
		return baseDao.update(id);
	}

	public boolean update(Map map) {
		// TODO Auto-generated method stub
		return baseDao.update(map);
	}
}