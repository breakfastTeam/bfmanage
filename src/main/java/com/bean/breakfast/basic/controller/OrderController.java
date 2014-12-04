package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfOrder;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IStringUtil;
import com.bean.plugin.upyun.UPUPload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class OrderController {
    @Autowired
    private OrderService orderService;

    Page<TBfFood> page = new Page<TBfFood>(IConstants.DEFAULT_PAGE_SIZE);
    Page<FoodDTO> pageDTO = new Page<FoodDTO>(IConstants.DEFAULT_PAGE_SIZE);


    /**
     * 跳转到订单管理页面带查询条件
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toOrder")
    public ModelAndView toOrder(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/food");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String foodName = request.getParameter("foodName");
        TBfOrder order = new TBfOrder();

        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
//        pageDTO = orderService.(page, order);
        model.addObject("page", page);
        model.addObject("setName", order);
        return model;
    }
    /**
     * 跳转到菜谱管理页面不带查询条件
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToFood() {
        ModelAndView model = new ModelAndView("basic/order");
//        TBfOrder order = new TBfOrder();
//        pageDTO = orderService.findFood(page, order);
        model.addObject("page", page);
        return model;
    }
}
