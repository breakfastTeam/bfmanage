package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.dto.SendCouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfSendCoupon;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.SendCouponService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/basic")
public class SendCouponController {
    Page<SendCouponCustomerDTO> pageDTO = new Page<SendCouponCustomerDTO>(IConstants.DEFAULT_PAGE_SIZE);
    Page<TBfSendCoupon> page = new Page<TBfSendCoupon>(IConstants.DEFAULT_PAGE_SIZE);

    @Autowired
    private SendCouponService sendCouponService;

    /**
     * 保存供应商信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveSendCoupon", method = RequestMethod.POST)
    public ModelAndView saveSendCoupon(final HttpServletRequest request) {
        String sendCouponId = request.getParameter("sendCouponId");
        String priceStr = request.getParameter("price");
        String numStr = request.getParameter("num");
        String uids = request.getParameter("uids");
        int num = IStringUtil.isNotBlank(numStr)?Integer.parseInt(numStr):1;
        double price = IStringUtil.isNotBlank(priceStr)?Double.parseDouble(priceStr):1;

        TBfSendCoupon sendCoupon;
        if(IStringUtil.isNotBlank(sendCouponId)){
            try {
                sendCoupon = sendCouponService.getSendCoupon(sendCouponId);
            } catch (Exception e) {
                e.printStackTrace();
                sendCoupon = new TBfSendCoupon();
            }
        }else{
            sendCoupon = new TBfSendCoupon();
        }
        sendCoupon.setSource(uids);
        sendCoupon.setNum(num);
        sendCoupon.setPrice(price);
        sendCoupon.setCreateTime(IDateUtil.getCurrentTimeDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,30);
        sendCoupon.setEndTime(calendar.getTime());
        sendCoupon.setStatus(IConstants.ENABLE);

        sendCouponService.saveOrUpdate(sendCoupon);
        return jumpToSendCoupon();
    }
    /**
     * 跳转到发红包页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toSendCoupon")
    public ModelAndView toSendCoupon(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/sendCoupon");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }

        TBfSendCoupon sendCoupon = new TBfSendCoupon();
        pageDTO = sendCouponService.findSendCoupon(page, sendCoupon);
        model.addObject("page", pageDTO);
        return model;
    }

    /**
     * 跳转到发红包管理页面
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToSendCoupon() {
        ModelAndView model = new ModelAndView("basic/sendCoupon");
        TBfSendCoupon sendCoupon = new TBfSendCoupon();
        pageDTO = sendCouponService.findSendCoupon(page, sendCoupon);
        model.addObject("page", pageDTO);
        return model;
    }

    /**
     * 跳转到添加发红包的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toSendCouponAdd")
    public ModelAndView toSendCouponAdd(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/sendCouponAdd");
        return model;
    }
    @RequestMapping(value = "/toSendCouponDelete")
    public ModelAndView toSendCouponEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/sendCouponAdd");
        String sendCouponId = request.getParameter("sendCouponId");
        TBfSendCoupon sendCoupon = sendCouponService.getSendCoupon(sendCouponId);
        sendCoupon.setStatus(IConstants.DISCARD);
        sendCouponService.saveOrUpdate(sendCoupon);
        return jumpToSendCoupon();
    }
}
