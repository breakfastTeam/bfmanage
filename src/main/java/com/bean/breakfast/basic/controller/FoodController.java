package com.bean.breakfast.basic.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IImageUtil;
import com.bean.plugin.upyun.UPUPload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    Page<FoodDTO> pageDTO = new Page<FoodDTO>(IConstants.DEFAULT_PAGE_SIZE);
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
        String diskPath = request.getParameter("diskPath");
        String orginPicPath = request.getParameter("orginPicPath");
        String smallPicPath =  request.getParameter("smallPicPath");
        String tempOrginPicPath = request.getSession().getServletContext().getRealPath(orginPicPath);
        String tempSmallPicPath = request.getSession().getServletContext().getRealPath(smallPicPath);
        dealThePic(diskPath, tempOrginPicPath, tempSmallPicPath);
        String smallPicId = request.getParameter("smallPicId");
        String orginPicId = request.getParameter("orginPicId");
        String foodId = request.getParameter("foodId");
        String foodName = request.getParameter("foodName");
        String priceStr = request.getParameter("price");
        Double price = Double.parseDouble(priceStr);
        String costStr = request.getParameter("cost");
        Double cost = Double.parseDouble(costStr);
        String unit = request.getParameter("unit");
        String foodCountStr = request.getParameter("foodCount");
        int foodCount = Integer.parseInt(foodCountStr);
        String realFoodCountStr = request.getParameter("realFoodCount");
        int realFoodCount = Integer.parseInt(realFoodCountStr);

        String isSupportSnapUpStr = request.getParameter("isSupportSnapUp");
        byte isSupportSnapUp = IStringUtil.equals(IConstants.ON, isSupportSnapUpStr) ? IConstants.YES : IConstants.NO;
        String snapUpPriceStr = request.getParameter("snapUpPrice");
        Double snapUpPrice = IStringUtil.isNotBlank(snapUpPriceStr) ? Double.parseDouble(snapUpPriceStr):0;
        String isSupportExchangeStr = request.getParameter("isSupportExchange");
        byte isSupportExchange = IStringUtil.equals(IConstants.ON, isSupportExchangeStr) ? IConstants.YES : IConstants.NO;
        String exchangePriceStr = request.getParameter("exchangePrice");
        int exchangePrice =  IStringUtil.isNotBlank(exchangePriceStr) ? Integer.parseInt(exchangePriceStr):0;
        String showOrderStr = request.getParameter("showOrder");
        int showOrder = Integer.parseInt(showOrderStr);
        String briefIntro = request.getParameter("briefIntro");
        String saleTime = request.getParameter("saleTime");
        TBfFood food;
        if(IStringUtil.isNotBlank(foodId)){
            try {
                food = foodService.getFood(foodId);
            } catch (Exception e) {
                e.printStackTrace();
                food = new TBfFood();
            }
        }else{
            food = new TBfFood();
        }
        food.setFoodName(foodName);
        food.setCost(cost);
        food.setPrice(price);
        food.setUnit(unit);
        food.setFoodCount(foodCount);
        food.setRealFoodCount(realFoodCount);
        food.setSupportSnapUp(isSupportSnapUp);
        food.setSupportExchange(isSupportExchange);
        food.setExchangeCount(exchangePrice);
        food.setShowOrder(showOrder);//排序号
        food.setBriefIntro(briefIntro);
        food.setCreateTime(IDateUtil.getCurrentTimeDate());
        food.setSaleTime(IDateUtil.parseDate(saleTime, 1));

        foodService.saveOrUpdate(food, smallPicId, smallPicPath, orginPicId, orginPicPath);
        return jumpToFood();
    }
    public void dealThePic(String filePath, String orginPicPath, String smallPicPath){
        IImageUtil.scaleImage(filePath, orginPicPath, IConstants.FOOD_BIG_PIC_WIDTH, IConstants.FOOD_BIG_PIC_HEIGHT);
        IImageUtil.scaleImage(filePath, smallPicPath, IConstants.FOOD_SMALL_PIC_WIDTH, IConstants.FOOD_SMALL_PIC_HEIGHT);

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
    public String uploadFoodPic(@RequestParam(value = "files", required = false) MultipartFile file, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从搜索0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = year + "-" + month + "-" + day;
        String filePath = request.getSession().getServletContext().getRealPath(IConstants.FOOD_PIC_PATH) + "\\" + time;
        String originalFileName = file.getOriginalFilename();
        int length = originalFileName.split("\\.").length;
        String suffix = originalFileName.split("\\.")[length - 1];
        String fileName = cal.getTimeInMillis() + "." + suffix;
        boolean isCreate = IFileUtil.createUploadFile(filePath, fileName, file);
        Map<String, Object> map = new HashMap<String, Object>();
        if (isCreate) {
            map.put("filePath", IConstants.FOOD_PIC_PATH + "\\" + time+"\\"+fileName);
            map.put("originalFileName", originalFileName);
            map.put("orginPicPath", IConstants.FOOD_PIC_PATH + "\\" + time+"\\orginal\\"+fileName);
            map.put("smallPicPath", IConstants.FOOD_PIC_PATH + "\\" + time+"\\small\\"+fileName);
            map.put("diskPath", filePath+"\\"+fileName);

        } else {
            map.put("fileName", IConstants.ERROR);
            map.put("filePath", IConstants.ERROR);
        }

        return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
    }
   /** @ResponseBody
    @RequestMapping(value = "/uploadFoodPic.do")
    public String uploadFoodPic(@RequestParam(value = "files", required = false) MultipartFile file, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        String originalFileName = file.getOriginalFilename();
        int length = originalFileName.split("\\.").length;
        String suffix = originalFileName.split("\\.")[length - 1];
        Calendar cal = Calendar.getInstance();//使用日历类
        String fileName = cal.getTimeInMillis() + "." + suffix;
        String localFilePath = "";
        String path = request.getPathInfo();
        String path2 = request.getContextPath();
        String path3 = request.getPathTranslated();
        try{
            UPUPload upUpload = new UPUPload();
            String orginFilePath = upUpload.uploadImage(file.getBytes(), fileName);
            String picHeight = upUpload.getUpyun().getPicHeight();
            String picWidth = upUpload.getUpyun().getPicWidth();

            Map<String, Object> map = new HashMap<String, Object>();
            if(StringUtils.isNotBlank(picHeight) && picHeight.equals(IConstants.FOOD_BIG_PIC_HEIGHT)
                    && StringUtils.isNotBlank(picWidth) && picWidth.equals(IConstants.FOOD_BIG_PIC_WIDTH)){
                String smallFileName = "small_"+fileName;
                String scaleFilePath = upUpload.scaleImage(file.getBytes(), smallFileName, IConstants.FOOD_SMALL_PIC_WIDTH + "x" + IConstants.FOOD_SMALL_PIC_HEIGHT);
                map.put("fileName", originalFileName);
                map.put("filePath", orginFilePath);
                map.put("scaleFilePath", scaleFilePath);
                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
            }else{
                boolean success = upUpload.deleteImage(fileName);
                System.out.println(success);
                return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
            }
        }catch (Exception e){
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
    }
    **/
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
    @RequestMapping(value = "/deleteFoodPic")
    public String deleteFoodPic(@RequestBody final String reqData, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String orginPicPath = bodyObj.getString("orginPicPath");
            String orginPicPathAbsolute = request.getSession().getServletContext().getRealPath(orginPicPath);

            String orginPicPathAbsolutes[] = orginPicPathAbsolute.split("\\\\");
            String filePath = "";
            int lgh = orginPicPathAbsolutes.length;
            for(int i = 0; i<lgh-2; i++){
                filePath = filePath +orginPicPathAbsolutes[i]+"\\";
            }
            filePath = filePath +"\\"+orginPicPathAbsolutes[lgh-1];
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
        int showOrder = foodService.getOrderNum() + 1;
        FoodDTO food = new FoodDTO();
        food.setShowOrder(showOrder);
        model.addObject("food", food);
        model.addObject("validTime", IDateUtil.dateToString(new Date()));
        return model;
    }
    @RequestMapping(value = "/toEditFood")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/addFood");
        String foodId = request.getParameter("foodId");
        FoodDTO foodDTO = foodService.getFoodDTO(foodId);
        model.addObject("food", foodDTO);
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
        pageDTO = foodService.findFood(page, food);
        model.addObject("page", page);
        model.addObject("foodName",foodName );
        return model;
    }
    /**
     * 跳转到菜谱管理页面
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    public ModelAndView jumpToFood() {
        ModelAndView model = new ModelAndView("basic/food");
        TBfFood food = new TBfFood();
        pageDTO = foodService.findFood(page, food);
        model.addObject("page", page);
        return model;
    }
}
