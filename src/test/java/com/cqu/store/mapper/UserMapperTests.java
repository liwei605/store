package com.cqu.store.mapper;

import com.cqu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest


public class UserMapperTests {


    //调整错误等级可以消除该处无法自动装配的下划线报错
    @Autowired
    private UserMapper userMapper;

    //用户插入测试
    @Test
    public void insert_user()
    {
        User user =new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer flag =userMapper.insert(user);
        System.out.println(flag);
    }

    //用户查询设置通过ID查找
    @Test
    public void select_user()
    {
        User user =userMapper.findByUid(2);
        System.out.println(user);
    }

    @Test
    //通过用户ID更新密码
    public void updatePasswordByUid()
    {
        userMapper.updatePasswordByUid(1,"654321","admin change",new Date());
    }
    @Test
    //通过用户ID查询用户
    public void findByUid()
    {
        User user=  userMapper.findByUid(1);
        System.out.println(user);
    }

    @Test
    //通过用户ID查询用户
    public void updateInfoByid()
    {
        User user=new User();
        user.setUid(10);
        user.setGender(1);
        user.setPhone("17843939721");
        user.setEmail("2499280864@qq.com");

        userMapper.updateInfoByUid(user);

    }

    @Test
    public void updateAvatarByUid()
    {
        //头像信息
        userMapper.updateAvatarByUid(10,"/index/user.png","liwie1",new Date());
    }

}
