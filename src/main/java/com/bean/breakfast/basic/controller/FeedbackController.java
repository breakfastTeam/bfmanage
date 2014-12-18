package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.model.TBfFeedback;
import com.bean.breakfast.basic.service.FeedbackService;
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
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    Page<TBfFeedback> page = new Page<TBfFeedback>(IConstants.DEFAULT_PAGE_SIZE);

    
    /**
     * 跳转到反馈内容管理页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toFeedback")
    public ModelAndView toProvider(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/feedback");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        TBfFeedback feedback = new TBfFeedback();
        

        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = feedbackService.findFeedback(page, feedback);
        model.addObject("page", page);
        return model;
    }
    /**
     * 跳转到意见反馈管理页面
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToProvider() {
        ModelAndView model = new ModelAndView("basic/feedback");
        TBfFeedback feedback = new TBfFeedback();
        page = feedbackService.findFeedback(page, feedback);
        model.addObject("page", page);
        return model;
    }
}
