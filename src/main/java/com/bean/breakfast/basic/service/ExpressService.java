package com.bean.breakfast.basic.service;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.core.orm.service.BaseService;
import com.bean.core.page.Page;

import java.util.List;

public interface ExpressService extends BaseService<TBfExpress, String> {
	public void saveOrUpdate(TBfExpress express);
	public List<TBfExpress> getExpressByCourierId(String courierId);
	public void updateCourierPostion(String courierId, String longitude, String latitude) throws Exception;
}
