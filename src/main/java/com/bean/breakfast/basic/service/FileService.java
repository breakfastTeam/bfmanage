package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.RawMaterialDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface FileService extends BaseService<TBfFile, String> {
	public void saveOrUpdate(TBfFile file);
}
