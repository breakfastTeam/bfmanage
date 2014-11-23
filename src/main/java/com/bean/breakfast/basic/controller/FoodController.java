package com.bean.breakfast.basic.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.SetMealService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IStringUtil;

@Controller
@RequestMapping("/basic")
public class FoodController {
    @Autowired
    private FoodService foodService;

    Page<TBfFood> page = new Page<TBfFood>(IConstants.DEFAULT_PAGE_SIZE);

    /**
     * 保存菜谱信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveFood", method = RequestMethod.POST)
    public ModelAndView saveCookBook(final HttpServletRequest request) {
        String xs = request.getParameter("x");
        String ys = request.getParameter("y");
        int x = IStringUtil.isNotBlank(xs) ? Integer.parseInt(xs) : 0;
        int y = IStringUtil.isNotBlank(ys) ? Integer.parseInt(ys) : 0;
        String filePath = request.getParameter("filePath");
        String cropFileName = request.getParameter("cropFileName");

        String realFilePath = request.getSession().getServletContext().getRealPath(filePath) + "\\" + cropFileName;
        String scaleFilePath = request.getSession().getServletContext().getRealPath(filePath) + "\\scale\\";
        int size[] = IImageUtil.getSize(realFilePath);
        double scale = 1;
        if (size[0] > IConstants.FOOD_BIG_PIC_WIDTH && size[1] > IConstants.FOOD_BIG_PIC_HEIGHT) {
            scale = IImageUtil.getScale(size[0], size[0], IConstants.FOOD_BIG_PIC_WIDTH, IConstants.FOOD_BIG_PIC_HEIGHT);
        }
        IImageUtil.scaleImage(realFilePath, scaleFilePath, scale);
        String smallFilePath = filePath + "\\" + "small\\";
        String bigFilePath = filePath + "\\" + "big\\";
        IImageUtil.cutImage(scaleFilePath + cropFileName, request.getSession().getServletContext().getRealPath(bigFilePath), x, y, IConstants.FOOD_BIG_PIC_WIDTH, IConstants.FOOD_BIG_PIC_HEIGHT);
        IImageUtil.scaleImage(request.getSession().getServletContext().getRealPath(bigFilePath) + "\\" + cropFileName, request.getSession().getServletContext().getRealPath(smallFilePath), IConstants.HALF);
        smallFilePath = smallFilePath + cropFileName;
        bigFilePath = bigFilePath + cropFileName;

        String foodName = request.getParameter("foodName");
        String priceStr = request.getParameter("price");
        Double price = Double.parseDouble(priceStr);
        String validTime = request.getParameter("validTime");
        String invalidTime = request.getParameter("invalidTime");
        String isTimeLimitBuyStr = request.getParameter("isTimeLimitBuy");
        String isTimeLimitBuy = IStringUtil.equals(IConstants.ON, isTimeLimitBuyStr) ? IConstants.YES : IConstants.NO;
        String exchangeMoneyStr = request.getParameter("exchangeMoney");
        Double exchangeMoney = IStringUtil.isNotBlank(exchangeMoneyStr) ? Double.parseDouble(exchangeMoneyStr) : price;
        String orderNumStr = request.getParameter("orderNum");
        int orderNum = Integer.parseInt(orderNumStr);
        String briefIntro = request.getParameter("briefIntro");

        TBfFood food = new TBfFood();
        food.setFoodName(foodName);
        food.setPrice(price);
        food.setBriefIntro(briefIntro);
        foodService.save(food);

        return toFood(request);
    }

    /**
     * 将菜谱的图片上传到后台
     *
     * @return fileName String 文件名称
     * @author Felix
     * @since 2014-06-7 16:13
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFoodPic.do")
    public String uploadCookBookPic(@RequestParam(value = "files", required = false) MultipartFile file, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从搜索0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = year + "-" + month + "-" + day;
        String cookBookPath = request.getSession().getServletContext().getRealPath(IConstants.FOOD_PIC_PATH) + "\\" + time;
        String originalFileName = file.getOriginalFilename();
        int length = originalFileName.split("\\.").length;
        String suffix = originalFileName.split("\\.")[length - 1];
        String fileName = cal.getTimeInMillis() + "." + suffix;
        boolean isCreate = IFileUtil.createUploadFile(cookBookPath, fileName, file);
        Map<String, Object> map = new HashMap<String, Object>();
        if (isCreate) {
            map.put("fileName", originalFileName);
            map.put("filePath", IConstants.FOOD_PIC_PATH + "\\\\" + time + "\\\\");
            map.put("cropFileName", fileName);
            map.put("saveDiskPath", time + "\\\\" + fileName);
        } else {
            map.put("fileName", IConstants.ERROR);
            map.put("filePath", IConstants.ERROR);
        }

        return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
    }

    /**
     * 删除菜谱图片
     *
     * @param reqData String 请求的报文字符串
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/deleteFood")
    public String deleteCookBook(@RequestBody final String reqData, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String cookBookPath = request.getSession().getServletContext().getRealPath(IConstants.FOOD_PIC_PATH);
            String filePath = cookBookPath + "\\" + bodyObj.getString("filePath") + "\\\\" + bodyObj.getString("cropFileName");
            boolean isDelete = IFileUtil.deleteFile(filePath);
            if (isDelete) {
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
            } else {
                return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, IConstants.SERVER_EXCEPTION).generateRtnMsg();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }

    /**
     * 跳转到添加菜谱的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toAddFood")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/addFood");
        int orderNum = foodService.getOrderNum() + 1;
        model.addObject("orderNum", orderNum);
        model.addObject("validTime", IDateUtil.dateToString(new Date()));
        return model;
    }

    /**
     * 跳转到菜谱管理页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toFood")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/food");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String foodName = request.getParameter("foodName");
        TBfFood food = new TBfFood();
        if (IStringUtil.isNotBlank(foodName)) {
            food.setFoodName(foodName);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = foodService.findFood(page, food);
        model.addObject("page", page);
        model.addObject("setName", food);
        return model;
    }
}
