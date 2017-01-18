package com.ld.web.biz;

import com.ld.web.been.Page;
import com.ld.web.been.model.Dict;

/**
 * 
 *<p>Title: DictBiz</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-12-08
 */
public interface DictBiz {

    void delete(Dict dict);

    Page<Dict> getPage(Page<Dict> page, String typeId, String value, String name);
}
