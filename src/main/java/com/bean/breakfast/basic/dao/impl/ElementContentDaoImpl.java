package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ElementContentDao;
import com.bean.breakfast.basic.dao.ElementDao;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfElementContent;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("elementContentDao")
public class ElementContentDaoImpl extends BaseDaoImpl<TBfElementContent, String> implements ElementContentDao {

    @Override
    public List<TBfElementContent> getElementContentByObjId(String objId) {
        String hql = "from TBfElementContent t where t.objectId=?";
        return this.find(hql,objId);
    }
}
