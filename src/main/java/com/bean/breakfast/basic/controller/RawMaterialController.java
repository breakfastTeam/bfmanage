package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.breakfast.basic.service.ProviderService;
import com.bean.breakfast.basic.service.RawMaterialService;
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

@Controller
@RequestMapping("/basic")
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;
    Page<TBfRawMaterial> page = new Page<TBfRawMaterial>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 保存原材料信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveRawMaterial", method = RequestMethod.POST)
    public ModelAndView saveRawMaterial(final HttpServletRequest request) {
        String rawMaterialId = request.getParameter("rawMaterialId");
        String rawMaterialName = request.getParameter("rawMaterialName");
        String priceStr = request.getParameter("price");
        Double price = IStringUtil.isNotBlank(priceStr)?Double.parseDouble(priceStr):0;
        String unit = request.getParameter("unit");

        TBfRawMaterial rawMaterial;
        if(IStringUtil.isNotBlank(rawMaterialId)){
            try {
                rawMaterial = rawMaterialService.getRawMaterial(rawMaterialId);
                rawMaterial.setLastModifyTime(IDateUtil.getCurrentTimeDate());
            } catch (Exception e) {
                e.printStackTrace();
                rawMaterial = new TBfRawMaterial();
                rawMaterial.setCreateTime(IDateUtil.getCurrentTimeDate());
            }
        }else{
            rawMaterial = new TBfRawMaterial();
            rawMaterial.setCreateTime(IDateUtil.getCurrentTimeDate());
        }
        rawMaterial.setRawMaterialName(rawMaterialName);
        rawMaterial.setUnit(unit);
        rawMaterial.setPrice(price);
        rawMaterial.setStatus(IConstants.ENABLE);

        rawMaterialService.saveOrUpdate(rawMaterial);
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

    @RequestMapping(value = "/toRawMaterial")
    public ModelAndView toProvider(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/rawMaterial");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String rawMaterialName = request.getParameter("rawMaterialName");
        TBfRawMaterial rawMaterial = new TBfRawMaterial();
        if (IStringUtil.isNotBlank(rawMaterialName)) {
            rawMaterial.setRawMaterialName(rawMaterialName);
        }

        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = rawMaterialService.findRawMaterial(page, rawMaterial);
        model.addObject("page", page);
        model.addObject("rawMaterialName",rawMaterialName);

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
        ModelAndView model = new ModelAndView("basic/rawMaterial");
        TBfRawMaterial rawMaterial = new TBfRawMaterial();
        page = rawMaterialService.findRawMaterial(page, rawMaterial);
        model.addObject("page", page);
        return model;
    }

    /**
     * 跳转到添加原材料的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toRawMaterialAdd")
    public ModelAndView toRawMaterialAdd(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/rawMaterialAdd");

        return model;
    }
    @RequestMapping(value = "/toRawMaterialEdit")
    public ModelAndView toRawMaterialEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/rawMaterialAdd");
        String rawMaterialId = request.getParameter("rawMaterialId");
        TBfRawMaterial rawMaterial = rawMaterialService.getRawMaterial(rawMaterialId);
        model.addObject("rawMaterial", rawMaterial);
        return model;
    }
}
