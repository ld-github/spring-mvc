package com.ld.web.biz;

import com.ld.web.been.Page;
import com.ld.web.been.model.DictType;

/**
 * 
 *<p>Title: DictTypeBiz</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
public interface DictTypeBiz {

    void save(DictType dictType);

    DictType get(String code);

    void delete(DictType dictType);

    Page<DictType> getPage(Page<DictType> page, String code, String name);
}
