package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.ld.web.been.ServerResp;
import com.ld.web.been.model.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.util.EncryptionUtil;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: UserController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-03
 */
@Controller
@Scope("prototype")
@RequestMapping(UserController.REQUEST_INDEX_URL)
public class UserController extends BaseController {

    private static final long serialVersionUID = 596021065899369405L;

    public static final String REQUEST_INDEX_URL = "/user";

    @Resource
    private UserBiz userBiz;

    @RequestMapping(value = "/toLogin")
    public String toLogin(String username, String password, String verificationCode) {

        if (StringUtil.isEmpty(username)) {
            putReqAttributes(new ServerResp(false, "请输入用户名"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        if (StringUtil.isEmpty(password)) {
            putReqAttributes(new ServerResp(false, "请输入密码"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        if (StringUtil.isEmpty(verificationCode)) {
            putReqAttributes(new ServerResp(false, "请输入验证码"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        String sessionCode = (String) getSessionObj(Constants.KAPTCHA_SESSION_KEY);

        if (!verificationCode.equalsIgnoreCase(sessionCode)) {
            putReqAttributes(new ServerResp(false, "验证码输入错误"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        removeSessionUser();

        User user = userBiz.get(username);

        if (null == user || !EncryptionUtil.sha256(password).equals(user.getPassword())) {
            putReqAttributes(new ServerResp(false, "用户名或密码输入不正确"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        if (!user.getAvailable()) {
            putReqAttributes(new ServerResp(false, "用户状态不可用"));
            return PageController.REQUEST_PAGE_URL_LOGIN;
        }

        putSessionUser(user);
        return redirect(PageController.REQUEST_PAGE_URL_MAIN);
    }

    @RequestMapping(value = "/toLogout")
    public String toLogout() {
        removeSessionUser();
        return redirect(PageController.REQUEST_PAGE_URL_LOGIN);
    }

}
