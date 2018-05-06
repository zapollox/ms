package com.zx.ms.service;


import com.zx.ms.dao.UserDao;
import com.zx.ms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DELL on 2018/4/27.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getById(Integer id){
        User user = userDao.getById(id);
        return user;
    }
}
