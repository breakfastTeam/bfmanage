package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.ElementDao;
import com.bean.breakfast.basic.dao.InformationDao;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import com.bean.core.page.Page;
import com.bean.core.utils.IStringUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("elementDao")
public class ElementDaoImpl extends BaseDaoImpl<TBfElement, String> implements ElementDao {

}
