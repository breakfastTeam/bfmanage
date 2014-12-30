package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.ProviderService;
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
import java.util.Date;

@Controller
@RequestMapping("/basic")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    Page<TBfProvider> page = new Page<TBfProvider>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 保存供应商信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveProvider", method = RequestMethod.POST)
    public ModelAndView saveInformation(final HttpServletRequest request) {
        String providerid = request.getParameter("providerId");
        String providerName = request.getParameter("providerName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String alternativePhone = request.getParameter("alternativePhone");

        TBfProvider provider;
        if(IStringUtil.isNotBlank(providerid)){
            try {
                provider = providerService.getProvider(providerid);
            } catch (Exception e) {
                e.printStackTrace();
                provider = new TBfProvider();
            }
        }else{
            provider = new TBfProvider();
        }
        provider.setProviderName(providerName);
        provider.setAddress(address);
        provider.setCreateTime(IDateUtil.getCurrentTimeDate());
        provider.setStatus(IConstants.ENABLE);
        provider.setAlternativePhone(alternativePhone);
        provider.setPhone(phone);

        providerService.saveOrUpdate(provider);
        return jumpToProvider();
    }
    /**
     * 跳转到供应商管理页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toProvider")
    public ModelAndView toProvider(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/provider");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String providerName = request.getParameter("providerName");
        String phone = request.getParameter("phone");
        TBfProvider provider = new TBfProvider();
        if (IStringUtil.isNotBlank(providerName)) {
            provider.setProviderName(providerName);
        }
        if(IStringUtil.isNotBlank(phone)){
            provider.setPhone(phone);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = providerService.findProvider(page, provider);
        model.addObject("page", page);
        model.addObject("providerName",providerName);
        model.addObject("phone",phone );

        return model;
    }
    /**
     * 跳转到供应商管理页面
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToProvider() {
        ModelAndView model = new ModelAndView("basic/provider");
        TBfProvider provider = new TBfProvider();
        page = providerService.findProvider(page, provider);
        model.addObject("page", page);
        return model;
    }

    /**
     * 跳转到添加公告的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toProviderAdd")
    public ModelAndView toProviderAdd(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/providerAdd");

        return model;
    }
    @RequestMapping(value = "/toProviderEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/providerAdd");
        String providerId = request.getParameter("providerId");
        TBfProvider provider = providerService.getProvider(providerId);
        model.addObject("provider", provider);
        return model;
    }
}
