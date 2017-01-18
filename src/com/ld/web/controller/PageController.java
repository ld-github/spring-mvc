package com.ld.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *<p>Title: PageController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping
public class PageController extends BaseController {

    private static final long serialVersionUID = -3915106687680179329L;

    public static final String REQUEST_PAGE_URL_LANDING = "/landing";
    public static final String REQUEST_PAGE_URL_LOGIN = "/login";
    public static final String REQUEST_PAGE_URL_MAIN = "/main";
    public static final String REQUEST_PAGE_URL_INDEX = "/index";
    public static final String REQUEST_PAGE_URL_PASSWORD = "/password";
    public static final String REQUEST_PAGE_URL_DICT = "/dict";

    @RequestMapping(value = REQUEST_PAGE_URL_LANDING)
    public String landing() {
        return "landing";
    }

    @RequestMapping(value = REQUEST_PAGE_URL_LOGIN)
    public String login() {
        return "login";
    }

    @RequestMapping(value = REQUEST_PAGE_URL_MAIN)
    public String main() {
        return "main";
    }

    @RequestMapping(value = REQUEST_PAGE_URL_INDEX)
    public String index() {
        return "index";
    }

    @RequestMapping(value = REQUEST_PAGE_URL_PASSWORD)
    public String password() {
        return "password";
    }

    @RequestMapping(value = REQUEST_PAGE_URL_DICT)
    public String dict() {
        return "dict";
    }

}
