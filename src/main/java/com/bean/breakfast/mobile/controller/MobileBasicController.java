package com.bean.breakfast.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/mobile")
public class MobileBasicController {
	@RequestMapping(value = "/")
	public ModelAndView home(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/index");
		return model;
	}
	@RequestMapping(value = "/foodList")
	public ModelAndView foodList(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/foodList");
		return model;
	}
	@RequestMapping(value = "/foodDetail")
	public ModelAndView foodDetail(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/foodDetail");
		return model;
	}
	@RequestMapping(value = "/toMyOrders")
	public ModelAndView toMyOrders(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/myOrders");
		return model;
	}
	@RequestMapping(value = "/toBuyNow")
	public ModelAndView toOrderNow(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/buyNow");
		return model;
	}

	@RequestMapping(value = "/500")
	public ModelAndView error500(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/common/500");
		return model;
	}
	@RequestMapping(value = "/404")
	public ModelAndView error404(final HttpServletRequest request){
		ModelAndView model = new ModelAndView("/mobile/common/404");
		return model;
	}
}
