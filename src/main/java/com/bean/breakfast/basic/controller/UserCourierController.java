package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.dto.UserCourierDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;
import com.bean.breakfast.basic.service.UserCourierService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.ISecurityUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/basic")
public class UserCourierController {
    @Autowired
    private UserCourierService userCourierService;

    @Autowired
    private UserService userService;

    Page<UserCourierDTO> page = new Page<UserCourierDTO>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 保存用户信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveUserCourier", method = RequestMethod.POST)
    public ModelAndView saveUserCourier(final HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String loginName = request.getParameter("loginName");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String qq = request.getParameter("qq");
        String weixin = request.getParameter("weixin");
        String source = request.getParameter("source");

        TBfUser user;
        TBfUserCourier userCourier;
        if(IStringUtil.isNotBlank(userId)){
            try {
                user = userService.getUser(userId);
                userCourier = userCourierService.getUserCourier(userId);
                if(!IStringUtil.equals(user.getPassword(), password)){
                    password = ISecurityUtil.getMD5String(password);
                }
            } catch (Exception e) {
                e.printStackTrace();
                user = new TBfUser();
                userCourier = new TBfUserCourier();
            }
        }else{
            user = new TBfUser();
            userCourier = new TBfUserCourier();
            password = ISecurityUtil.getMD5String(password);
        }
        user.setMobile(mobile);
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setUserName(userName);
        user.setQq(qq);
        user.setWeixin(weixin);
        user.setStatus(IConstants.VALID);
        user.setCreateTime(IDateUtil.getCurrentTimeDate());
        user.setRegisterTime(IDateUtil.getCurrentTimeDate());

        userCourier.setSource(source);
        userCourier.setCreateTime(IDateUtil.getCurrentTimeDate());

        UserCourierDTO userCourierDTO = new UserCourierDTO();
        userCourierDTO.setCourier(userCourier);
        userCourierDTO.setUser(user);
        userCourierService.saveOrUpdate(userCourierDTO);

        return jumpToCourier();
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

    @RequestMapping(value = "/toUserCourierAdd")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCourierAdd");

        return model;
    }

    @RequestMapping(value = "/toUserCourierEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCourierAdd");
        String userId = request.getParameter("userId");
        UserCourierDTO userCourierDTO = userCourierService.getUserCourierDTO(userId);
        model.addObject("userCourierDTO", userCourierDTO);
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

    @RequestMapping(value = "/toUserCourier")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/userCourier");
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
        TBfUserCourier userCourier = new TBfUserCourier();
        UserCourierDTO userCourierDTO = new UserCourierDTO();
        userCourierDTO.setUser(user);
        userCourierDTO.setCourier(userCourier);

        page = userCourierService.findUserCourierDTO(page, userCourierDTO);
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

    public ModelAndView jumpToCourier(){
        ModelAndView model = new ModelAndView("basic/userCourier");
        TBfUser user = new TBfUser();
        TBfUserCourier userCourier = new TBfUserCourier();
        UserCourierDTO userCourierDTO = new UserCourierDTO();
        userCourierDTO.setUser(user);
        userCourierDTO.setCourier(userCourier);
        page = userCourierService.findUserCourierDTO(page, userCourierDTO);
        model.addObject("page", page);
        return model;
    }
}
