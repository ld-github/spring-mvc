package com.ld.web.biz;

import com.ld.web.been.model.User;

/**
 * 
 * <p>Title: UserBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public interface UserBiz {

    /**
     * Save User
     * 
     * @param user
     * 
     */
    void save(User user);

    /**
     * 
     * @return
     */
    long getTotal();

    /**
     * 
     * @param username
     * @return
     */
    User get(String username);
}
