package com.cqu.store.service;

import com.cqu.store.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    //注册业务测试
    @Test
    public void reg_test()
    {
        User user =new User();
        user.setUsername("liwei1");
        //带有加密
        user.setPassword("123456");
        userService.reg(user);

    }
    @Test
    public void login_test()
    {
        User user=  userService.login("liwei","123456");
        System.out.println(user);
    }

    @Test
    public void changerPassword_test()
    {
        userService.changerPassword(10,"liwei1","123456","654321");
    }

    @Test
    public void getByUid() {
        User user = userService.getByUid(10);
        System.err.println(user);
    }

    @Test
    public void changeInfo() {
            Integer uid = 10;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, username, user);
            System.out.println("OK.");
    }

    @Test
    public  void changeAvator()
    {
        userService.changeAvatar(10,"liwei1","/upload/test.png");
    }

}
