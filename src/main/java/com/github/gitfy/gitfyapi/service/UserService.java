package com.github.gitfy.gitfyapi.service;


import com.github.gitfy.gitfyapi.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;



}
