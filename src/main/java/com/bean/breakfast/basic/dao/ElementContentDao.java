package com.bean.breakfast.basic.dao;

import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfElementContent;
import com.bean.core.orm.dao.BaseDao;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
public interface ElementContentDao extends BaseDao<TBfElementContent, String> {
    public List<TBfElementContent> getElementContentByObjId(String objId);
}
