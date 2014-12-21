package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.page.Page;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/basic")
public class CouponController {
    Page<CouponCustomerDTO> pageDTO = new Page<CouponCustomerDTO>(IConstants.DEFAULT_PAGE_SIZE);
    Page<TBfCoupon> page = new Page<TBfCoupon>(IConstants.DEFAULT_PAGE_SIZE);

    @Autowired
    private CouponService couponService;

    /**
     * 跳转到菜谱管理页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toCoupon")
    public ModelAndView toCoupon(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/coupon");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }

        TBfCoupon coupon = new TBfCoupon();
        pageDTO = couponService.findCoupon(page, coupon);
        model.addObject("page", pageDTO);
        return model;
    }
}
