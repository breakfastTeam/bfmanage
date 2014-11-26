package com.bean.breakfast.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mobile")
public class MobileFoodController {
	@Autowired
	private FoodService foodService;

	Page<TBfFood> page = new Page<TBfFood>(IConstants.DEFAULT_PAGE_SIZE);
	Page<FoodDTO> pageDTO = new Page<FoodDTO>(IConstants.DEFAULT_PAGE_SIZE);
	/**
	 * 获取菜谱信息
	 * @param reqData String 请求的报文字符串
	 * @return model ModelAndView 基本返回对象
	 * @author Felix
	 * @since  2014-04-19 9:59
	 * 变更记录:
	 *
	 */
	@ResponseBody
	@RequestMapping(value = "/getFood")
	public String getCookBook(@RequestBody final String reqData, final HttpServletRequest request){
		MsgUtil msgUtil = new MsgUtil();//声明报文工具类
		JSONObject json;//报文对应的JSON对象
		JSONObject bodyObj;
		try {
			/**解析处理请求报文**/
			json=JSONObject.parseObject(reqData);
			bodyObj=json.getJSONObject("body");
			String pageSizeStr = bodyObj.getString("pageSize");
			String pageNoStr = bodyObj.getString("pageNo");

			TBfFood food = new TBfFood();
			if(StringUtils.isNotBlank(pageSizeStr) && StringUtils.isNotBlank(pageNoStr)){
				page.setPageSize(Integer.parseInt(pageSizeStr));
				page.setPageNo(Integer.parseInt(pageNoStr));
			}
			pageDTO = foodService.findFood(page, food);
			return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(pageDTO.getResult());

		}catch(Exception e){
			e.printStackTrace();
			return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
		}
	}
}
