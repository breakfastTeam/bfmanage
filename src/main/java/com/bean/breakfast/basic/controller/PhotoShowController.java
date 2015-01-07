package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.dto.CouponCustomerDTO;
import com.bean.breakfast.basic.model.TBfCoupon;
import com.bean.breakfast.basic.model.TBfFile;
import com.bean.breakfast.basic.model.TBfPhotoShow;
import com.bean.breakfast.basic.service.CouponService;
import com.bean.breakfast.basic.service.FileService;
import com.bean.breakfast.basic.service.PhotoShowService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.IFileUtil;
import com.bean.core.utils.IImageUtil;
import com.bean.core.utils.IStringUtil;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;

@Controller
@RequestMapping("/basic")
public class PhotoShowController {

    @Autowired
    private PhotoShowService photoShowService;

    /**
     * 上传厨房照片
     * @param request HttpServletRequest request请求
     * @return model ModelAndView 基本返回对象
     * @author Felix
     * @since 2014-04-19 9:59
     * 变更记录:
     */

    @ResponseBody
    @RequestMapping(value = "/uploadPhotoShow.do", method = RequestMethod.POST)
    public String uploadFoodPic(@RequestParam(value = "uploadfile", required = false) MultipartFile file,final HttpServletRequest request) {
        MsgUtil msgUtil = new MsgUtil();//声明报文工具类
        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从搜索0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        String time = year + "-" + month + "-" + day;
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath(IConstants.PHOTO_SHOW_PATH) + File.separator + time;
        boolean isCreate = IFileUtil.createUploadFile(filePath, fileName, file);
        String smallPicPath = IConstants.PHOTO_SHOW_PATH + File.separator + "small"+File.separator + fileName;
        IImageUtil.scaleImage(filePath+File.separator+fileName, smallPicPath, IConstants.PHOTO_SMALL_PIC_WIDTH, IConstants.PHOTO_SMALL_PIC_HEIGHT);

        TBfFile bigPic = new TBfFile();
        bigPic.setFilePath(IConstants.PHOTO_SHOW_PATH + File.separator + fileName);
        bigPic.setStatus(IConstants.ENABLE);
        bigPic.setCreateTime(IDateUtil.getCurrentTimeDate());

        TBfFile smallPic = new TBfFile();
        smallPic.setCreateTime(IDateUtil.getCurrentTimeDate());
        smallPic.setStatus(IConstants.ENABLE);
        smallPic.setFilePath(smallPicPath);


        TBfPhotoShow photoShow = new TBfPhotoShow();
        photoShow.setStatus(IConstants.ENABLE);
        photoShow.setCreateTime(IDateUtil.getCurrentTimeDate());
        photoShow.setOrginFileId(bigPic.getFileId());
        photoShow.setSmallFileId(smallPic.getFileId());
        photoShow.setUploadTime(IDateUtil.getCurrentTimeDate());
        photoShowService.saveOrUpdate(photoShow, bigPic, smallPic);

        return msgUtil.generateHeadMsg(IConstants.SUCCESS_CODE, IConstants.OPERATE_SUCCESS).generateRtnMsg();
    }
}
