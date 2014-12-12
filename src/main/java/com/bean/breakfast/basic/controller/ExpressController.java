package com.bean.breakfast.basic.controller;

import com.bean.breakfast.basic.model.TBfExpress;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.service.ExpressService;
import com.bean.breakfast.basic.service.UserService;
import com.bean.breakfast.constants.IConstants;
import com.bean.breakfast.utils.MsgUtil;
import com.bean.core.page.Page;
import com.bean.core.utils.IDateUtil;
import com.bean.core.utils.ISecurityUtil;
import com.bean.core.utils.IStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class ExpressController {
    @Autowired
    private ExpressService expressService;
}
