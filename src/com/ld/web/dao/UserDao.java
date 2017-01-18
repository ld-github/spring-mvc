package com.ld.web.dao;

import com.ld.web.been.model.User;

/**
 * 
 * <p>Title: UserDao</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
public interface UserDao extends BaseDao<User> {

    User get(String username);
}
