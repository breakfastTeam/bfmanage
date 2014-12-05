package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
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
import java.util.*;

@Controller
@RequestMapping("/basic")
public class OrderController {
    @Autowired
    private OrderService orderService;

    Page<TBfOrder> page = new Page<TBfOrder>(IConstants.DEFAULT_PAGE_SIZE);
    Page<OrderDTO> pageDTO = new Page<OrderDTO>(IConstants.DEFAULT_PAGE_SIZE);


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
        ModelAndView model = new ModelAndView("basic/order");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String consigneeName = request.getParameter("consigneeName");
        String consigneeMobile = request.getParameter("consigneeMobile");
        String consigneeAddress = request.getParameter("consigneeAddress");
        TBfOrder order = new TBfOrder();
        order.setConsigneeAddress(consigneeAddress);
        order.setConsigneeMobile(consigneeMobile);
        order.setConsigneeName(consigneeName);
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        pageDTO = orderService.findOrders(pageDTO, order);
        model.addObject("page", pageDTO);
        model.addObject("consigneeName",consigneeName);
        model.addObject("consigneeMobile",consigneeMobile);
        model.addObject("consigneeAddress",consigneeAddress);

        return model;
    }


    /**
     * 跳转到订单详情页面
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/toOrderDetail")
    public ModelAndView toOrderDetail(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/orderDetail");
        String orderId = request.getParameter("orderId");
        List<TBfFood> foods = orderService.getOrderDetail(orderId);
        model.addObject("foods", foods);
        return model;
    }

}
