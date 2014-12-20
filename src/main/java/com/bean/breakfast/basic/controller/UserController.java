package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.InformationService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.ISecurityUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class UserController {
    @Autowired
    private UserService userService;

    Page<TBfUser> page = new Page<TBfUser>(IConstants.DEFAULT_PAGE_SIZE);


    /**
     * 按照登陆名获取用户信息
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     * 变更记录：chy 2014-04-22
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(final HttpServletRequest request, final HttpServletResponse response){
        RequestContext requestContext = new RequestContext(request);

        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        String loginName=request.getParameter("loginName");
        String password=request.getParameter("password");

        String securityPassword = ISecurityUtil.getMD5String(password);
        TBfUser user = userService.getUserByLoginName(loginName);

        if(user == null){//用户不存在
            return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE,requestContext.getMessage("USER_NOT_EXIST")).generateRtnMsg();
        }else{//用户存在
            if(!IStringUtil.equals(user.getPassword().toLowerCase(), securityPassword.toLowerCase())){//用户名或密码不正确
                return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, requestContext.getMessage("USER_NAME_OR_PASSWORD_IS_WRONG")).generateRtnMsg();
            }else{//登录成功
                request.getSession().setAttribute(IConstants.LOGINED, IConstants.IS_LOGINED);
                request.getSession().setAttribute(IConstants.USER_INFO, user);
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE,requestContext.getMessage("LOGIN_SUCCESS")).generateRtnMsg();
            }
        }
    }

    /**
     * 更改用户状态
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     * 变更记录：chy 2014-04-22
     */

    @ResponseBody
    @RequestMapping(value = "/updateUserStatus")
    public String updateUserStatus(@RequestBody final String reqData){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String userId = bodyObj.getString("userId");
            String status = bodyObj.getString("status");
            TBfUser user = userService.getUser(userId);
            if(user != null){
                user.setStatus(status);
                user.setLastModifyTime(IDateUtil.getCurrentTimeDate());
                userService.saveOrUpdate(user);
            }
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    /**
     * 校验登录名和手机号是否重复
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     * 变更记录：chy 2014-04-22
     */

    @ResponseBody
    @RequestMapping(value = "/checkLoginNameAndPhone")
    public String checkLoginNameAndPhone(@RequestBody final String reqData){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String loginName = bodyObj.getString("loginName");
            String phone = bodyObj.getString("phone");
            TBfUser user = userService.getUserByLoginName(loginName);
            if(user != null){
                return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, "用户名已存在！").generateRtnMsg();
            }else{
                user = userService.getUserByPhone(phone);
                if(user != null){
                    return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, "手机号码已存在！").generateRtnMsg();
                }else{
                    return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    /**
     * 保存用户信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(final HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String loginName = request.getParameter("loginName");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        String userName = request.getParameter("userName");
        String qq = request.getParameter("qq");
        String weixin = request.getParameter("weixin");

        TBfUser user;
        if(IStringUtil.isNotBlank(userId)){
            try {
                user = userService.getUser(userId);
                if(!IStringUtil.equals(user.getPassword(), password)){
                    password = ISecurityUtil.getMD5String(password);
                }
            } catch (Exception e) {
                e.printStackTrace();
                user = new TBfUser();
            }
        }else{
            user = new TBfUser();
            password = ISecurityUtil.getMD5String(password);
        }
        user.setMobile(mobile);
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setUserName(userName);
        user.setQq(qq);
        user.setWeixin(weixin);
        user.setStatus(IConstants.ENABLE);
        user.setUserType(IConstants.USER_TYPE_ADMIN);
        user.setCreateTime(IDateUtil.getCurrentTimeDate());
        user.setRegisterTime(IDateUtil.getCurrentTimeDate());
        userService.saveOrUpdate(user);

        return jumpToUser();
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

    @RequestMapping(value = "/toUserAdd")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/userAdd");

        return model;
    }

    @RequestMapping(value = "/toUserEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userAdd");
        String userId = request.getParameter("userId");
        TBfUser user = userService.getUser(userId);
        model.addObject("user", user);
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

    @RequestMapping(value = "/toUser")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/user");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String mobile = request.getParameter("mobile");
        TBfUser user = new TBfUser();
        user.setUserType(IConstants.USER_TYPE_ADMIN);
        if (IStringUtil.isNotBlank(mobile)) {
            user.setMobile(mobile);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = userService.findUser(page, user);
        model.addObject("page", page);
        model.addObject("mobile",mobile );
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

    public ModelAndView jumpToUser(){
        ModelAndView model = new ModelAndView("basic/user");
        TBfUser user = new TBfUser();
        page = userService.findUser(page, user);
        model.addObject("page", page);
        return model;
    }
}
