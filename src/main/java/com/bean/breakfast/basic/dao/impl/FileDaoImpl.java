package com.bean.breakfast.basic.dao.impl;

import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.core.orm.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by qingfeilee on 2014/11/24.
 */
@Repository("fileDao")
public class FileDaoImpl  extends BaseDaoImpl<TBfFile,String> implements FileDao {
}
