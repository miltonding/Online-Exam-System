package com.augmentun.exam.service.impl;

import com.augmentun.exam.dao.UserDao;
import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.exception.ServiceException;
import com.augmentun.exam.model.User;
import com.augmentun.exam.service.UserService;
import com.augmentun.exam.util.StringUtil;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) throws ParamterException, ServiceException{
        User user = null;
        ParamterException paramterException = new ParamterException();
        //validate
        if (StringUtil.validateParam(username) == false) {
            paramterException.addErrorField("TIP_USERNAME", "userName is required");
        }
        if (StringUtil.validateParam(password) == false) {
            paramterException.addErrorField("TIP_PASSWORD", "password is required");
        }

        if (paramterException.hasErrorMessage()) {
            throw paramterException;
        }
        user = userDao.getUserByName(username);
        if (user ==null) {
            throw new ServiceException(1000,"useName doesn't exist");
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new ServiceException(1001,"password is incorrect");
        }
    }
}
