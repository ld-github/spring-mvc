package com.ld.web.controller;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ld.web.been.model.User;
import com.ld.web.util.JsonMapper;

/**
 * 
 *<p>Title: BaseController</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-2
 */
@Controller
@Scope("prototype")
public class BaseController implements Serializable {

    private static final long serialVersionUID = 5249682224462255858L;

    private final String SESSION_USER_KEY = "user";

    public Object getSessionObj(String key) {
        return getSession().getAttribute(key);
    }

    public void putSessionObj(String key, Object obj) {
        getSession().setAttribute(key, obj);
    }

    public void removeSessionObj(String key) {
        getSession().removeAttribute(key);
    }

    public void putSessionUser(User user) {
        getSession().setAttribute(SESSION_USER_KEY, user);
    }

    public Object getSesstionUser() {
        return getSessionObj(SESSION_USER_KEY);
    }

    public void removeSessionUser() {
        removeSessionObj(SESSION_USER_KEY);
    }

    public void putReqAttributes(Object obj) {

        String json = JsonMapper.getInstance().toJson(obj);

        Map<String, Object> data = JsonMapper.getInstance().toObject(json, new TypeReference<HashMap<String, Object>>() {
        });

        HttpServletRequest request = getRequest();
        for (Entry<String, Object> entry : data.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

    }

    /**
     * Get request
     * 
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Get response
     * 
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * Get session
     * 
     * @return
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Redirect to location
     * 
     * @param url
     * @return
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }

    public void writerPrint(HttpServletResponse resp, String buffer) {
        PrintWriter out = null;
        try {
            resp.setContentType("text/html; charset=utf-8");
            out = resp.getWriter();
            out.print(buffer);
            out.flush();
        } catch (Exception e) {
        }
    }
}
