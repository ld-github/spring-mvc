package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.Page;
import com.ld.web.been.model.DictType;
import com.ld.web.biz.DictTypeBiz;
import com.ld.web.dao.DictTypeDao;

/**
 * 
 *<p>Title: DictTypeBizImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Service
@Transactional
public class DictTypeBizImpl implements DictTypeBiz {

    @Resource
    private DictTypeDao dictTypeDao;

    @Override
    public void save(DictType dictType) {
        dictTypeDao.save(dictType);
    }

    @Override
    public DictType get(String code) {
        return dictTypeDao.get(code);
    }

    @Override
    public void delete(DictType dictType) {
        dictTypeDao.delete(dictType);
    }

    @Override
    public Page<DictType> getPage(Page<DictType> page, String code, String name) {
        return dictTypeDao.getPage(page, code, name);
    }
}
