package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;
import com.ld.web.biz.DictBiz;

/**
 * 
 *<p>Title: DictController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-16
 */
@Controller
@Scope("prototype")
@RequestMapping(DictController.REQUEST_INDEX_URL)
public class DictController extends BaseController {

    private static final long serialVersionUID = 5783046897297987491L;

    public static final String REQUEST_INDEX_URL = "/dict";

    @Resource
    private DictBiz dictBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    private Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name) {

        return dictBiz.getPage(page, typeId, value, name);
    }
}
