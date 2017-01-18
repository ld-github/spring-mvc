package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.been.model.User;
import com.ld.web.biz.UserBiz;
import com.ld.web.dao.UserDao;

/**
 * 
 * <p>Title: UserBizImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Service
@Transactional
public class UserBizImpl implements UserBiz {

    @Resource
    private UserDao userDao;

    @Override
    public void save(User user) {
        user.init();
        userDao.save(user);
    }

    @Override
    public long getTotal() {
        return userDao.getTotal();
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

}
