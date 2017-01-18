package com.ld.web.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ld.web.controller.BaseController;
import com.ld.web.controller.PageController;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: SessionFilter</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-02
 */
public class SessionFilter extends BaseController implements HandlerInterceptor {

    private static final long serialVersionUID = 5721961777596018631L;

    private List<String> excludedUrls;

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception e) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object obj, ModelAndView mv) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
        String requestUri = req.getRequestURI();

        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }

        if (null == getSesstionUser()) {
            String xRequestedWith = req.getHeader("X-Requested-With");

            if (!StringUtil.isEmpty(xRequestedWith) && xRequestedWith.equalsIgnoreCase("XMLHttpRequest")) {
                resp.sendError(518, "Session timeout...");
                return false;
            }

            String url = req.getContextPath() + PageController.REQUEST_PAGE_URL_LOGIN;
            writerPrint(resp, "<SCRIPT>window.top.location.href = '" + url + "'</SCRIPT>");
            return false;
        }

        return true;
    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

}
