package com.bean.breakfast.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mobile")
public class MobileExpressController {
    @Autowired
    private ExpressService expressService;


    /**
     * 更新配送员经纬度
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     */
    @ResponseBody
    @RequestMapping(value = "/updateCourierPostion")
    public String updateCourierPostion(@RequestBody final String reqData, final HttpServletRequest request){
        JSONObject json;
        JSONObject bodyObj;
        json = JSONObject.parseObject(reqData);
        bodyObj = json.getJSONObject("body");
        String courierId = bodyObj.getString("courierId");
        String longitude = bodyObj.getString("longitude");
        String latitude = bodyObj.getString("latitude");
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类

        try {
            expressService.updateCourierPostion(courierId, longitude, latitude);
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE,IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE,IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
}
