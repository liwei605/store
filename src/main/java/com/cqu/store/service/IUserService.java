package com.cqu.store.service;


import com.cqu.store.entity.User;

//用户模块业务层接口
public interface IUserService {
    void reg(User user);
    User login(String username,String password);
    void changerPassword(Integer uid,String username,String oldPassword,String newPassword);

    User getByUid(Integer uid);

    void changeInfo(Integer uid, String username, User user);

    void changeAvatar(Integer uid, String username, String avatar);
}
