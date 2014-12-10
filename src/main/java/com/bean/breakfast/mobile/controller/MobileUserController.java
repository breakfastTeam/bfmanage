package com.bean.breakfast.mobile.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.OrderDTO;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.utils.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
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
            String phone = bodyObj.getString("phone");

            TBfUser user = userService.getUserByPhone(phone);
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
    /**
     * 根据微信号获取用户信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/regist")
    public String regist(@RequestBody final String reqData, final HttpServletRequest request){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;//报文对应的JSON对象
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json=JSONObject.parseObject(reqData);
            bodyObj=json.getJSONObject("body");
            String phone = bodyObj.getString("phone");
            TBfUser userTemp = userService.getUserByPhone(phone);
            if(userTemp == null){
                TBfUser user = new TBfUser();
                user.setMobile(phone);
                String userId = userService.saveUser(user);
                if(userId != null){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("userId", user.getUserId());
                    return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
                }else{
                    return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, IConstants.WEIXIN_USER_NOT_EXIST).generateRtnMsg();
                }
            }else{
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userId", userTemp.getUserId());
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
            }

        }catch(Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    /**
     * 获取验证码图片
     *@author qingfeilee
     *@param
     **/
    @RequestMapping(value = "/getValidateCodeImage", method = RequestMethod.POST)
    @ResponseBody
    public String getValidateCodeImage(final HttpServletRequest request, final HttpServletResponse response){
        String imageString = "";
        RandomValidateCodeUtil randomValidateCodeUtil  = new RandomValidateCodeUtil();
        BufferedImage image = randomValidateCodeUtil.getRandcode(request, response);
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", bos);//将内存中的图片通过流动形式输出到客户端
            byte [] b = bos.toByteArray();
            imageString = encoder.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageString;
    }
    /**
     * 获取验证码
     *@author qingfeilee
     *@param
     **/
    @RequestMapping(value = "/getValidateCode")
    @ResponseBody
    public String getValidateCode(final HttpServletRequest request, final HttpServletResponse response){
        return (String) request.getSession().getAttribute(IConstants.RANDOM_ENABLEATE_CODE_KEY);
    }
}
