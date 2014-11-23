package com.bean.breakfast.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kkk on 14/11/19.
 */
@Controller
@RequestMapping("/basic")
public class FoodController {
    @RequestMapping(value = "/")
    public ModelAndView welcome(final HttpServletRequest request){
        ModelAndView model = new ModelAndView("index");
        return model;
    }
}
