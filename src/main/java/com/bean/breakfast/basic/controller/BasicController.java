package com.bean.breakfast.basic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/")
public class BasicController {
    /**
     * 登录成功后进入系统首页
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     *
     */

    @RequestMapping(value = "/home")
    public ModelAndView home(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("/index");
        return model;
    }
    /**
     * 定义用户在浏览器中输入项目地址后的跳转到的欢迎页或者登录页面
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     *
     */

    @RequestMapping(value = "/")
    public ModelAndView welcome(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("common/login");
        return model;
    }
    /**
     * 发生404错误
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     *
     */

    @RequestMapping(value = "/404")
    public ModelAndView error404(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("/common/404");
        return model;
    }

    /**
     * 发生404错误
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     *
     */

    @RequestMapping(value = "/500")
    public ModelAndView error500(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("/common/500");
        return model;
    }

    /**
     * 打开图片编辑页面
     * @param request  HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since  2014-04-19 9:59
     * 变更记录:
     *
     */

    @RequestMapping(value = "/openImageCrop")
    public ModelAndView openImageCrop(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("/common/imageCrop");
        String path = request.getParameter("path");
        String cropFileName= request.getParameter("cropFileName");
        model.addObject("path", path);
        model.addObject("cropFileName",cropFileName);
        return model;
    }
}
