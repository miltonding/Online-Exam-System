package com.augmentun.exam.service;

import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.exception.ServiceException;
import com.augmentun.exam.model.User;

public interface UserService {

    public User login (String username, String password) throws ParamterException, ServiceException;
}
