package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.model.User;
import com.ld.web.biz.SystemBiz;
import com.ld.web.biz.UserBiz;

/**
 * 
 *<p>Title: SystemBizImpl</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-11
 */
@Service
@Transactional
public class SystemBizImpl implements SystemBiz {

    @Resource
    private UserBiz userBiz;

    @Override
    public void initBasicData() {

        if (userBiz.getTotal() == 0) {
            User user = new User("admin", "!QAZ2wsx");
            user.init();

            userBiz.save(user);
        }

    }
}
