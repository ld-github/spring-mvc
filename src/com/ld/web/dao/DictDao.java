package com.ld.web.dao;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;

/**
 * 
 *<p>Title: DictDao</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
public interface DictDao extends BaseDao<Dict> {

    Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name);

}
