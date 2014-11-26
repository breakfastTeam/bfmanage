package com.bean.breakfast.mobile.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.OrderService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.mobile.dto.UserDTO;
import com.bean.breakfast.utils.MsgUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mobile")
public class MobileUserController {
    @Autowired
    private UserService userService;

    /**
     * 根据微信号获取用户信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo(@RequestBody final String reqData, final HttpServletRequest request){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;//报文对应的JSON对象
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json=JSONObject.parseObject(reqData);
            bodyObj=json.getJSONObject("body");
            String weixin = bodyObj.getString("weixin");

            TBfUser user = userService.getUserByWeixin(weixin);
            if(user != null){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userName", user.getUserName());
                map.put("userId", user.getUserId());
                map.put("phone", user.getMobile());
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
            }else{
                return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, IConstants.WEIXIN_USER_NOT_EXIST).generateRtnMsg();
            }
        }catch(Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
}
