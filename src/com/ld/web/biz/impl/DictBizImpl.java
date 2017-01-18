package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;
import com.ld.web.biz.DictBiz;
import com.ld.web.dao.DictDao;

/**
 * 
 *<p>Title: DictBizImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Service
@Transactional
public class DictBizImpl implements DictBiz {

    @Resource
    private DictDao dictDao;

    @Override
    public void delete(Dict dict) {
        dictDao.delete(dict);
    }

    @Override
    public Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name) {
        return dictDao.getPage(page, typeId, value, name);
    }

}
