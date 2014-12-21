package com.bean.breakfast.basic.service.impl;

import com.bean.breakfast.basic.dao.ExpressDao;
import com.bean.breakfast.basic.dao.FileDao;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.FileService;
import com.bean.core.orm.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("fileService")
@Transactional
public class FileServiceImpl extends BaseServiceImpl<TBfFile,String> implements FileService {
	@Autowired
	private FileDao fileDao;

	public FileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
		super.setBaseDao(fileDao);
	}

	@Override
	public void saveOrUpdate(TBfFile file) {
		fileDao.saveOrUpdate(file);
	}


}
