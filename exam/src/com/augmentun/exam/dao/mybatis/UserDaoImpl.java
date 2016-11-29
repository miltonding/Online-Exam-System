package com.augmentun.exam.dao.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentun.exam.dao.UserDao;
import com.augmentun.exam.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{
    private static final String CLASS_NAME = User.class.getName();
    private static final String GET_USER_BY_NAME = ".getUserByName";

    @Override
    public User getUserByName(String username) {
        return getSqlSession().selectOne(CLASS_NAME + GET_USER_BY_NAME, username);
    }

}
