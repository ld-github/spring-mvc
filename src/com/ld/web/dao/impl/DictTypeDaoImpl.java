package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.been.Page;
import com.ld.web.been.model.DictType;
import com.ld.web.dao.DictTypeDao;
import com.ld.web.util.StringUtil;

/**
 * 
 *<p>Title: DictTypeDaoImpl</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
@Repository
public class DictTypeDaoImpl extends BaseDaoImpl<DictType> implements DictTypeDao {

    @Override
    public DictType get(String code) {
        String where = "WHERE o.code=:code";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);

        return super.getUniqueResult(where, params);
    }

    @Override
    public Page<DictType> getPage(Page<DictType> page, String code, String name) {
        String where = "WHERE o.canView=:canView ";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("canView", true);

        if (!StringUtil.isEmpty(code)) {
            where += "AND o.code LIKE :code ";
            params.put("code", "%" + code + "%");
        }
        if (!StringUtil.isEmpty(name)) {
            where += "AND o.name LIKE :name ";
            params.put("name", "%" + name + "%");
        }

        LinkedHashMap<String, String> orders = new LinkedHashMap<String, String>();
        orders.put("o.code", "asc");

        return getPage(where, params, orders, page);
    }

}
