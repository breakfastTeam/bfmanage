package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.dto.SetMealDTO;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.SetMealService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IImageUtil;
import com.bean.core.utils.IStringUtil;
import org.apache.commons.collections.iterators.ArrayListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/basic")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    @Autowired
    private FoodService foodService;

    Page<TBfSetMeal> page = new Page<TBfSetMeal>(IConstants.DEFAULT_PAGE_SIZE);
    Page<SetMealDTO> pageDTO = new Page<SetMealDTO>(IConstants.DEFAULT_PAGE_SIZE);
    /**
     * 保存菜谱信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveSetMeal", method = RequestMethod.POST)
    public ModelAndView saveSetMeal(final HttpServletRequest request) {
        String diskPath = request.getParameter("diskPath");
        String orginPicPath = request.getParameter("orginPicPath");
        String smallPicPath =  request.getParameter("smallPicPath");
        String tempOrginPicPath = request.getSession().getServletContext().getRealPath(orginPicPath);
        String tempSmallPicPath = request.getSession().getServletContext().getRealPath(smallPicPath);
        if(IStringUtil.isNotBlank(diskPath)){
            dealThePic(diskPath, tempOrginPicPath, tempSmallPicPath);
        }

        String smallPicId = request.getParameter("smallPicId");
        String orginPicId = request.getParameter("orginPicId");
        String setMealId = request.getParameter("setMealId");
        String setName = request.getParameter("setName");
        String priceStr = request.getParameter("price");
        Double price = Double.parseDouble(priceStr);
        String privilegeStr = request.getParameter("privilege");
        Double privilege = Double.parseDouble(privilegeStr);
        String foodCountStr = request.getParameter("foodCount");
        int foodCount = Integer.parseInt(foodCountStr);
        String realFoodCountStr = request.getParameter("realFoodCount");
        int realFoodCount = Integer.parseInt(realFoodCountStr);
        String showOrderStr = request.getParameter("showOrder");
        int showOrder = Integer.parseInt(showOrderStr);
        String introduce = request.getParameter("introduce");
//        String saleTime = request.getParameter("saleTime");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        List<TBfFood> foodList = new ArrayList<TBfFood>();
        String foodIds = request.getParameter("foodIds");
        if(IStringUtil.isNotBlank(foodIds)){
            String [] tempFoodIds = foodIds.split(",");
            for(int i = 0; i<tempFoodIds.length; i++){
                TBfFood f = foodService.getFood(tempFoodIds[i]);
                foodList.add(f);
            }
        }


        TBfSetMeal setMeal;
        if(IStringUtil.isNotBlank(setMealId)){
            try {
                setMeal = setMealService.getSetMeal(setMealId);
            } catch (Exception e) {
                e.printStackTrace();
                setMeal = new TBfSetMeal();
            }
        }else{
            setMeal = new TBfSetMeal();
        }
        setMeal.setSetName(setName);
        setMeal.setPrivilege(privilege);
        setMeal.setPrice(price);
        setMeal.setFoodCount(foodCount);
        setMeal.setRealFoodCount(realFoodCount);
        setMeal.setShowOrder(showOrder);//排序号
        setMeal.setIntroduce(introduce);
        setMeal.setCreateTime(IDateUtil.getCurrentTimeDate());
//        setMeal.setSaleTime(IDateUtil.parseDate(saleTime, 1));
        setMeal.setStartTime(IDateUtil.parseDate(startTime, 1));
        setMeal.setEndTime(IDateUtil.parseDate(endTime, 1));

        Calendar cal = Calendar.getInstance();
        String nowStr =cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        try {
            now = df.parse(nowStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (IDateUtil.diffDays(setMeal.getEndTime(),now)<0 || IDateUtil.diffDays(setMeal.getStartTime(),now)> 0) {
            setMeal.setStatus(IConstants.SOLDOUT);
        }else{
            setMeal.setStatus(IConstants.PUTAWAY);
        }

        SetMealDTO setMealDTO = new SetMealDTO();
        setMealDTO.setSetMeal(setMeal);
        setMealDTO.setFoods(foodList);
        setMealDTO.setOrginPicId(orginPicId);
        setMealDTO.setOrginPicPath(orginPicPath);
        setMealDTO.setSmallPicId(smallPicId);
        setMealDTO.setSmallPicPath(smallPicPath);

        setMealService.saveOrUpdate(setMealDTO);
        return jumpToSetMeal();
    }


    /**
     * 修改套餐状态
     * @param reqData String 请求的报文字符串
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @ResponseBody
    @RequestMapping(value = "/updateSetMealStatus")
    public String updateSetMealStatus(@RequestBody final String reqData, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String setMealId = bodyObj.getString("setMealId");
            String status = bodyObj.getString("status");
            TBfSetMeal setMeal = setMealService.getSetMeal(setMealId);
            setMeal.setStatus(status);
            setMeal.setLastModifyTime(IDateUtil.getCurrentTimeDate());
            setMealService.saveOrUpdate(setMeal);

            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
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
    @RequestMapping(value = "/uploadSetMealPic.do")
    public String uploadSetMealPic(@RequestParam(value = "files", required = false) MultipartFile file, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从搜索0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = year + "-" + month + "-" + day;
        String filePath = request.getSession().getServletContext().getRealPath(IConstants.SET_MEAL_PIC_PATH) + File.separator + time;
        String originalFileName = file.getOriginalFilename();
        String suffix = ".jpg";
        int dot = originalFileName.lastIndexOf('.');
        if ((dot >-1) && (dot < (originalFileName.length() - 1))) {
            suffix = originalFileName.substring(dot + 1);
        }
        String fileName = cal.getTimeInMillis() + "." + suffix;
        boolean isCreate = IFileUtil.createUploadFile(filePath, fileName, file);
        Map<String, Object> map = new HashMap<String, Object>();
        if (isCreate) {
            map.put("filePath", IConstants.SET_MEAL_PIC_PATH + File.separator + time+ File.separator+fileName);
            map.put("originalFileName", originalFileName);
            map.put("orginPicPath", IConstants.SET_MEAL_PIC_PATH +  File.separator + time+ File.separator+"orginal"+ File.separator+fileName);
            map.put("smallPicPath", IConstants.SET_MEAL_PIC_PATH +  File.separator + time+ File.separator+"small"+ File.separator+fileName);
            map.put("diskPath", filePath+ File.separator+fileName);

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
//    **/
//    /**
//     * 删除菜谱图片
//     *
//     * @param reqData String 请求的报文字符串
//     * @return model ModelAndView 基本返回对象
//     * @author Felix
//     * @since 2014-04-19 9:59
//     * 变更记录:
//     */
//    @ResponseBody
//    @RequestMapping(value = "/deleteFoodPic")
//    public String deleteFoodPic(@RequestBody final String reqData, final HttpServletRequest request) {
//        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
//        JSONObject json;
//        JSONObject bodyObj;
//        try {
//            /**解析处理请求报文**/
//            json = JSONObject.parseObject(reqData);
//            bodyObj = json.getJSONObject("body");
//            String orginPicPath = bodyObj.getString("orginPicPath");
//            String orginPicPathAbsolute = request.getSession().getServletContext().getRealPath(orginPicPath);
//
//            String orginPicPathAbsolutes[] = orginPicPathAbsolute.split("\\\\");
//            String filePath = "";
//            int lgh = orginPicPathAbsolutes.length;
//            for(int i = 0; i<lgh-2; i++){
//                filePath = filePath +orginPicPathAbsolutes[i]+"\\";
//            }
//            filePath = filePath +"\\"+orginPicPathAbsolutes[lgh-1];
//            boolean isDelete = IFileUtil.deleteFile(filePath);
//            if (isDelete) {
//                return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
//            } else {
//                return msgUtil.generateHeadMsg(IConstants.EXCEPTION_CODE, IConstants.SERVER_EXCEPTION).generateRtnMsg();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
//        }
//    }
//

    /**
     * 跳转到添加套餐的页面
     *
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @RequestMapping(value = "/toSetMealAdd")
    public ModelAndView toSetMealAdd(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/setMealAdd");
        int showOrder = setMealService.getShowOrder() + 1;
        SetMealDTO setMealDTO = new SetMealDTO();
        TBfSetMeal setMeal = new TBfSetMeal();
        setMeal.setShowOrder(showOrder);
        setMealDTO.setSetMeal(setMeal);
        model.addObject("setMealDTO", setMealDTO);
        return model;
    }
    @RequestMapping(value = "/toSetMealEdit")
    public ModelAndView toSetMealEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/setMealAdd");
        String setMealId = request.getParameter("setMealId");
        SetMealDTO setMealDTO = new SetMealDTO();
        if(IStringUtil.isNotBlank(setMealId)){
            setMealDTO = setMealService.getSetMealDTO(setMealId);
        }
        model.addObject("setMealDTO", setMealDTO);
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

    @RequestMapping(value = "/toSetMeal")
    public ModelAndView toSetMeal(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/setMeal");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String setName = request.getParameter("setName");
        TBfSetMeal setMeal = new TBfSetMeal();
        if (IStringUtil.isNotBlank(setName)) {
            setMeal.setSetName(setName);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = setMealService.findSetMeal(page, setMeal);
        model.addObject("page", page);
        model.addObject("setName",setName);
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

    public ModelAndView jumpToSetMeal() {
        ModelAndView model = new ModelAndView("basic/setMeal");
        TBfSetMeal setMeal = new TBfSetMeal();
        page = setMealService.findSetMeal(page, setMeal);
        model.addObject("page", page);
        return model;
    }
}
