package com.ld.web.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.web.been.Page;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictTypeBiz;

/**
 * 
 *<p>Title: DictTypeController</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-13
 */
@Controller
@Scope("prototype")
@RequestMapping(DictTypeController.REQUEST_INDEX_URL)
public class DictTypeController extends BaseController {

    private static final long serialVersionUID = -3666784443323247091L;

    public static final String REQUEST_INDEX_URL = "/dictType";

    @Resource
    private DictTypeBiz dictTypeBiz;

    @RequestMapping(value = "getPage")
    @ResponseBody
    private Page<DictType> getPage(Page<DictType> page, String code, String name) {

        return dictTypeBiz.getPage(page, code, name);
    }
}
