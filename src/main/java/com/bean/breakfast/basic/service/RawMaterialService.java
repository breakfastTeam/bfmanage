package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.RawMaterialDTO;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

public interface RawMaterialService extends BaseService<TBfRawMaterial, String> {
	public void saveOrUpdate(TBfRawMaterial rawMaterial);
	public Page<RawMaterialDTO> findRawMaterial(Page<TBfRawMaterial> page, TBfRawMaterial rawMaterial);
	public TBfRawMaterial getRawMaterial(String rawMaterialId);
}
