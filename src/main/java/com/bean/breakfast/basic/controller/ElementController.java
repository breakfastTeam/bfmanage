package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.model.TBfElement;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.service.ElementService;
import com.bean.breakfast.basic.service.FileService;
import com.bean.breakfast.basic.service.InformationService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IImageUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class ElementController {
    @Autowired
    private ElementService elementService;

    /**
     * 保存微量元素信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveElement", method = RequestMethod.POST)
    public ModelAndView saveElement(final HttpServletRequest request) {
        String elementName = request.getParameter("elementName");
        String unit = request.getParameter("unit");

        TBfElement element = new TBfElement();
        element.setElementName(elementName);
        element.setUnit(unit);
        elementService.saveOrUpdate(element);
        ModelAndView model = new ModelAndView("basic/elementAdd");
        return model;
    }

    /**
     * 跳转到添加微量元素信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/toAddElement")
    public ModelAndView toAddElement(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/elementAdd");
        return model;
    }

}
