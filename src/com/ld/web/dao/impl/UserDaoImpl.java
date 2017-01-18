package com.ld.web.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ld.web.been.model.User;
import com.ld.web.dao.UserDao;

/**
 * 
 * <p>Title: UserDaoImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User get(String username) {
        String where = "WHERE o.username=:username";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);

        return super.getUniqueResult(where, params);
    }

}
