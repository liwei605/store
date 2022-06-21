package com.cqu.store.service.impl;

import com.cqu.store.entity.User;
import com.cqu.store.mapper.UserMapper;
import com.cqu.store.service.IUserService;
import com.cqu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        //调用findByUsername判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        if (result != null) {
            //抛出异常，因为用户名被占用
            throw new UsernameDuplicatedException("用户名被占用，请更改用户名");
        }
        //密码加密处理  md5算法形式加密
        //盐值+密码+盐值  =========盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值（随机生成的）
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);

        //将密码和盐值作为一个整体加密
        String md5password = getMD5Password(oldPassword, salt);
        //加密完毕后将密码设置到User中
        user.setPassword(md5password);

        //补全信息,因为注册只需要输入用户名和密码
        //补全数据：is_delete=0
        user.setIsDelete(0);
        //补全4个信息：
        user.setCreatedUser(user.getAvatar());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务
        Integer flag = userMapper.insert(user);
        if (flag != 1) {
            throw new InsertException("用户在注册时发生了未知的异常!");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名称来查询用户的数据是否存在，如果不存在，则抛出异常
        User result = userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户密码是否匹配
        //1.先获取到数据库中的加密之后的密码
        String oldpassword = result.getPassword();
        //2.用户密码和加密进行比较
        //2.1 现获取盐值
        String salt = result.getSalt();
        //2.2 将用户的密码按照相同的md5算法进行加密
        String newMd5Password = getMD5Password(password, salt);

        if (!newMd5Password.equals(oldpassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断id_delete字段的值是否为1，表示用户已经删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        //调用mapper层的findbyusername来查询
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        user.setPassword(result.getPassword());
        user.setGender(result.getGender());
        //返回登录用户
        return user;
    }

    @Override
    public void changerPassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码和数据库中的密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!oldMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }

        //将新的密码设置到数据库中,将新的密码

        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        // 创建新的User对象
        User user = new User();
        // 将以上查询结果中的username/phone/email/gender封装到新User对象中
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        // 返回新的User对象
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 向参数user中补全数据：uid
        user.setUid(uid);
        // 向参数user中补全数据：modifiedUser(username)
        user.setModifiedUser(username);
        // 向参数user中补全数据：modifiedTime(new Date())
        user.setModifiedTime(new Date());
        // 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        Integer rows = userMapper.updateInfoByUid(user);

        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }

    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新用户头像产生未知的异常");
        }
    }


    //定义一个MD5加密类
    private String getMD5Password(String Password, String salt) {
        //md5加密算法调用三次加密
        for (int i = 0; i < 3; i++) {
            Password = DigestUtils.md5DigestAsHex((salt + Password + salt).getBytes()).toUpperCase();
        }
        return Password;
    }
}
