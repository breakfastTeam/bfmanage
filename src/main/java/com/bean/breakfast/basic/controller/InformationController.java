package com.bean.breakfast.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.breakfast.basic.dto.FoodDTO;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfFood;
import com.bean.breakfast.basic.model.TBfInformation;
import com.bean.breakfast.basic.model.TBfSetMeal;
import com.bean.breakfast.basic.service.FileService;
import com.bean.breakfast.basic.service.FoodService;
import com.bean.breakfast.basic.service.InformationService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IImageUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/basic")
public class InformationController {
    @Autowired
    private InformationService informationService;
    @Autowired
    private FileService fileService;



    Page<TBfInformation> page = new Page<TBfInformation>(IConstants.DEFAULT_PAGE_SIZE);
    /**
     * 保存公告信息
     *
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */
    @RequestMapping(value = "/saveInformation", method = RequestMethod.POST)
    public ModelAndView saveInformation(final HttpServletRequest request) {
        String informationId = request.getParameter("informationId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String postTimeStr = request.getParameter("postTime");
        String smallPicId= request.getParameter("smallPicId");
        String briefIntro = request.getParameter("briefIntro");

        Date postTime = IDateUtil.parseDate(postTimeStr, 1);
        TBfInformation information;
        if(IStringUtil.isNotBlank(informationId)){
            try {
                information = informationService.getInformation(informationId);
            } catch (Exception e) {
                e.printStackTrace();
                information = new TBfInformation();
            }
        }else{
            information = new TBfInformation();
        }
        information.setTitle(title);
        information.setContent(content);
        information.setSmallPicId(smallPicId);
        information.setBriefIntro(briefIntro);
        information.setCreateTime(IDateUtil.getCurrentTimeDate());
        information.setStatus(IConstants.ENABLE);
        information.setPostTime(IDateUtil.getCurrentTimeDate());
        informationService.saveOrUpdate(information);
        return jumpToFood();
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
    @RequestMapping(value = "/uploadInfoPic.do")
    public String uploadInfoPic(@RequestParam(value = "files", required = false) MultipartFile file, final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从搜索0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = year + "-" + month + "-" + day;
        String filePath = request.getSession().getServletContext().getRealPath(IConstants.INFO_PIC_PATH) + File.separator + time;
        String originalFileName = file.getOriginalFilename();
        String suffix = ".jpg";
        int dot = originalFileName.lastIndexOf('.');
        if ((dot >-1) && (dot < (originalFileName.length() - 1))) {
            suffix = originalFileName.substring(dot + 1);
        }
        String fileName = cal.getTimeInMillis() + "." + suffix;
        boolean isCreate = IFileUtil.createUploadFile(filePath, fileName, file);
        String orginPicPath = request.getSession().getServletContext().getRealPath(IConstants.INFO_PIC_PATH)+File.separator + time+File.separator+"small"+File.separator+fileName;
        IImageUtil.scaleImage(filePath+File.separator+fileName, orginPicPath, IConstants.INFO_SMALL_PIC_WIDTH, IConstants.INFO_SMALL_PIC_HEIGHT);

        TBfFile picFile = new TBfFile();
        picFile.setFilePath(IConstants.INFO_PIC_PATH+ File.separator + time+File.separator+"small"+File.separator+fileName);
        picFile.setCreateTime(IDateUtil.getCurrentTimeDate());
        fileService.saveOrUpdate(picFile);
        Map<String, String> map = new HashMap<String, String>();
        map.put("smallPicId", picFile.getFileId());
        map.put("originPicName", originalFileName);
        return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg(map);
    }
    /**
     * 更改信息状态
     * @author 李庆飞
     * @return User
     * @since 2014-04-19 10:45
     * 变更记录：chy 2014-04-22
     */

    @ResponseBody
    @RequestMapping(value = "/updateInfoStatus")
    public String updateInfoStatus(@RequestBody final String reqData){
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        JSONObject json;
        JSONObject bodyObj;
        try {
            /**解析处理请求报文**/
            json = JSONObject.parseObject(reqData);
            bodyObj = json.getJSONObject("body");
            String infoId = bodyObj.getString("infoId");
            String status = bodyObj.getString("status");
            TBfInformation info = informationService.getInformation(infoId);
            if(info != null){
                info.setStatus(status);
                info.setLastModifyTime(IDateUtil.getCurrentTimeDate());
                informationService.saveOrUpdate(info);
            }
            return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return msgUtil.generateHeadMsg(IConstants.ERROR_CODE, IConstants.OPERATE_ERROR).generateRtnMsg();
        }
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

    @RequestMapping(value = "/toInformationAdd")
    public ModelAndView toAddFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/basic/informationAdd");

        return model;
    }
    @RequestMapping(value = "/toInformationEdit")
    public ModelAndView toFoodEdit(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/informationAdd");
        String informationId = request.getParameter("informationId");
        TBfInformation information = informationService.getInformation(informationId);
        model.addObject("information", information);
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

    @RequestMapping(value = "/toInformation")
    public ModelAndView toFood(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("basic/information");
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        String title = request.getParameter("title");
        TBfInformation information = new TBfInformation();
        if (IStringUtil.isNotBlank(title)) {
            information.setTitle(title);
        }
        if (IStringUtil.isNotBlank(pageSizeStr) && IStringUtil.isNotBlank(pageNoStr)) {
            page.setPageNo(Integer.parseInt(pageNoStr));
        }
        page = informationService.findInformation(page, information);
        model.addObject("page", page);
        model.addObject("title",title );
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

    public ModelAndView jumpToFood(){
        ModelAndView model = new ModelAndView("basic/information");
        TBfInformation information = new TBfInformation();
        page = informationService.findInformation(page, information);
        model.addObject("page", page);
        return model;
    }
}
