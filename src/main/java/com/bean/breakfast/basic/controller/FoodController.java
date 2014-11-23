package com.bean.breakfast.basic.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.SetMealService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IStringUtil;

@Controller
@RequestMapping("/basic")
public class FoodController {
    @Autowired
    private FoodService foodService;

    Page<TBfFood> page = new Page<TBfFood>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 跳转到菜谱管理页面
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toFood")
    public ModelAndView toFood(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("basic/food");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String foodName = request.getParameter("foodName");
        TBfFood food = new TBfFood();
        if(IStringUtil.isNotBlank(foodName)){
            food.setFoodName(foodName);
        }
        if(IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)){
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = foodService.findFood(page, food);
        model.addObject("page", page);
        model.addObject("setName", food);
        return model;
    }
}
