package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.FoodService;
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
import java.util.*;

@Controller
@RequestMapping("/basic")
public class InformationController {
    @Autowired
    private InformationService informationService;

    Page<TBfInformation> page = new Page<TBfInformation>(IConstants.DEFAULT_PAGE_SIZE);
    /**
     * 保存公告信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveInformation", method = RequestMethod.POST)
    public ModelAndView saveInformation(final HttpServletRequest request) {
        String informationId = request.getParameter("informationId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String postTimeStr = request.getParameter("postTime");
        Date postTime = IDateUtil.parseDate(postTimeStr, 1);
        TBfInformation information;
        if(IStringUtil.isNotBlank(informationId)){
            try {
                information = informationService.getInformation(informationId);
            } catch (Exception e) {
                e.printStackTrace();
                information = new TBfInformation();
            }
        }else{
            information = new TBfInformation();
        }
        information.setTitle(title);
        information.setContent(content);
        information.setCreateTime(IDateUtil.getCurrentTimeDate());
        information.setStatus(IConstants.ENABLE);
        information.setPostTime(postTime);
        informationService.saveOrUpdate(information);
        return jumpToFood();
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

    @RequestMapping(value = "/toInformationAdd")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/informationAdd");

        return model;
    }
    @RequestMapping(value = "/toInformationEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/informationAdd");
        String informationId = request.getParameter("informationId");
        TBfInformation information = informationService.getInformation(informationId);
        model.addObject("information", information);
        return model;
    }
    /**
     * 跳转到公告管理页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toInformation")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/information");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String title = request.getParameter("title");
        TBfInformation information = new TBfInformation();
        if (IStringUtil.isNotBlank(title)) {
            information.setTitle(title);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = informationService.findInformation(page, information);
        model.addObject("page", page);
        model.addObject("title",title );
        return model;
    }
    /**
     * 跳转到公告管理页面
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToFood(){
        ModelAndView model = new ModelAndView("basic/information");
        TBfInformation information = new TBfInformation();
        page = informationService.findInformation(page, information);
        model.addObject("page", page);
        return model;
    }
}
