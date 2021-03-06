package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.dto.UserCustomerDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.model.TBfUserCustomer;
import com.bean.breakfast.basic.service.UserCourierService;
import com.bean.breakfast.basic.service.UserCustomerService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class UserCustomerController {
    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private UserService userService;

    Page<UserCustomerDTO> page = new Page<UserCustomerDTO>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 保存用户信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveUserCustomer", method = RequestMethod.POST)
    public ModelAndView saveUserCustomer(final HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String loginName = request.getParameter("loginName");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String qq = request.getParameter("qq");
        String weixin = request.getParameter("weixin");
        String creditsStr = request.getParameter("credits");
        int credits = 0;
        if(IStringUtil.isNotBlank(creditsStr)){
            credits = Integer.parseInt(creditsStr);
        }
        String customerLevel = request.getParameter("customerLevel");
        String referrer = request.getParameter("referrer");
        String recommendTimeStr = request.getParameter("recommendTime");
        Date recommendTime;
        if(IStringUtil.isNotBlank(recommendTimeStr)){
            recommendTime = IDateUtil.parseDate(recommendTimeStr, 1);
        }else{
            recommendTime = IDateUtil.getCurrentTimeDate();
        }


        TBfUser user;
        TBfUserCustomer userCustomer;
        if(IStringUtil.isNotBlank(userId)){
            try {
                user = userService.getUser(userId);
                userCustomer = userCustomerService.getUserCustomer(userId);
                if(!IStringUtil.equals(user.getPassword(), password)){
                    password = ISecurityUtil.getMD5String(password);
                }
            } catch (Exception e) {
                e.printStackTrace();
                user = new TBfUser();
                userCustomer = new TBfUserCustomer();
            }
        }else{
            user = new TBfUser();
            userCustomer = new TBfUserCustomer();
            password = ISecurityUtil.getMD5String(password);
        }
        user.setMobile(mobile);
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setUserName(userName);
        user.setQq(qq);
        user.setWeixin(weixin);
        user.setStatus(IConstants.ENABLE);
        user.setCreateTime(IDateUtil.getCurrentTimeDate());
        user.setRegisterTime(IDateUtil.getCurrentTimeDate());
        user.setUserType(IConstants.USER_TYPE_CUSTOMER);
        userCustomer.setCreateTime(IDateUtil.getCurrentTimeDate());
        userCustomer.setCredits(credits);
        userCustomer.setRecommendTime(recommendTime);
        userCustomer.setReferrer(referrer);
        userCustomer.setCustomerLevel(customerLevel);

        UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
        userCustomerDTO.setCustomer(userCustomer);
        userCustomerDTO.setUser(user);
        userCustomerService.saveOrUpdate(userCustomerDTO);

        return jumpToCustomer();
    }
    /**
     * 更改用户状态
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     * 变更记录：chy 2014-04-22
     */

    @ResponseBody
    @RequestMapping(value = "/updateUserCustomerStatus")
    public String updateUserCustomerStatus(@RequestBody final String reqData){
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
            TBfUserCustomer userCustomer = userCustomerService.getUserCustomer(userId);
            if(userCustomer != null){
                userCustomer.setLastModifyTime(IDateUtil.getCurrentTimeDate());
            }
            if(user != null){
                user.setStatus(status);
                user.setLastModifyTime(IDateUtil.getCurrentTimeDate());
            }
            UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
            userCustomerDTO.setCustomer(userCustomer);
            userCustomerDTO.setUser(user);
            userCustomerService.saveOrUpdate(userCustomerDTO);
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }

    /**
     * 跳转到添加快递员的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toUserCustomerAdd")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCustomerAdd");

        return model;
    }

    @RequestMapping(value = "/toUserCustomerEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCustomerAdd");
        String userId = request.getParameter("userId");
        UserCustomerDTO userCustomerDTO = userCustomerService.getUserCustomerDTO(userId);
        model.addObject("userCustomerDTO", userCustomerDTO);
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

    @RequestMapping(value = "/toUserCustomer")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCustomer");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String mobile = request.getParameter("mobile");
        TBfUser user = new TBfUser();
        if (IStringUtil.isNotBlank(mobile)) {
            user.setMobile(mobile);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        TBfUserCustomer userCustomer = new TBfUserCustomer();
        UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
        userCustomerDTO.setUser(user);
        userCustomerDTO.setCustomer(userCustomer);

        page = userCustomerService.findUserCustomerDTO(page, userCustomerDTO);
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

    public ModelAndView jumpToCustomer(){
        ModelAndView model = new ModelAndView("basic/userCustomer");
        TBfUser user = new TBfUser();
        TBfUserCustomer userCustomer = new TBfUserCustomer();
        UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
        userCustomerDTO.setUser(user);
        userCustomerDTO.setCustomer(userCustomer);
        page = userCustomerService.findUserCustomerDTO(page, userCustomerDTO);
        model.addObject("page", page);
        return model;
    }

    /**
     * 跳转到配送员选择页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toSendCouponCustomer")
    public ModelAndView toSendCouponCustomer(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/sendCouponCustomer");
        TBfUser user = new TBfUser();

        TBfUserCustomer userCustomer = new TBfUserCustomer();
        UserCustomerDTO userCustomerDTO = new UserCustomerDTO();
        userCustomerDTO.setUser(user);
        userCustomerDTO.setCustomer(userCustomer);

        List<UserCustomerDTO> sendCouponCustomers = userCustomerService.findUserCustomerDTO(userCustomerDTO);
        model.addObject("sendCouponCustomers", sendCouponCustomers);
        return model;
    }
}
