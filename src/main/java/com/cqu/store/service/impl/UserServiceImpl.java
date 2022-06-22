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
        //����findByUsername�ж��û��Ƿ�ע���
        User result = userMapper.findByUsername(username);
        if (result != null) {
            //�׳��쳣����Ϊ�û�����ռ��
            throw new UsernameDuplicatedException("�û�����ռ�ã�������û���");
        }
        //������ܴ���  md5�㷨��ʽ����
        //��ֵ+����+��ֵ  =========��ֵ����һ��������ַ���
        String oldPassword = user.getPassword();
        //��ȡ��ֵ��������ɵģ�
        String salt = UUID.randomUUID().toString().toUpperCase();
        //��ȫ���ݣ���ֵ�ļ�¼
        user.setSalt(salt);

        //���������ֵ��Ϊһ���������
        String md5password = getMD5Password(oldPassword, salt);
        //������Ϻ��������õ�User��
        user.setPassword(md5password);

        //��ȫ��Ϣ,��Ϊע��ֻ��Ҫ�����û���������
        //��ȫ���ݣ�is_delete=0
        user.setIsDelete(0);
        //��ȫ4����Ϣ��
        user.setCreatedUser(user.getAvatar());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //ִ��ע��ҵ��
        Integer flag = userMapper.insert(user);
        if (flag != 1) {
            throw new InsertException("�û���ע��ʱ������δ֪���쳣!");
        }
    }

    @Override
    public User login(String username, String password) {
        //�����û���������ѯ�û��������Ƿ���ڣ���������ڣ����׳��쳣
        User result = userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("�û����ݲ�����");
        }
        //����û������Ƿ�ƥ��
        //1.�Ȼ�ȡ�����ݿ��еļ���֮�������
        String oldpassword = result.getPassword();
        //2.�û�����ͼ��ܽ��бȽ�
        //2.1 �ֻ�ȡ��ֵ
        String salt = result.getSalt();
        //2.2 ���û������밴����ͬ��md5�㷨���м���
        String newMd5Password = getMD5Password(password, salt);

        if (!newMd5Password.equals(oldpassword)) {
            throw new PasswordNotMatchException("�û��������");
        }
        //�ж�id_delete�ֶε�ֵ�Ƿ�Ϊ1����ʾ�û��Ѿ�ɾ��
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("�û����ݲ�����");
        }

        //����mapper���findbyusername����ѯ
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        user.setPassword(result.getPassword());
        user.setGender(result.getGender());
        //���ص�¼�û�
        return user;
    }

    @Override
    public void changerPassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("�û����ݲ�����");
        }
        //ԭʼ��������ݿ��е�������бȽ�
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!oldMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("�������");
        }

        //���µ��������õ����ݿ���,���µ�����

        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());

        if (rows != 1) {
            throw new UpdateException("�������ݲ���δ֪�쳣");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("�û����ݲ�����");
        }

        // �����µ�User����
        User user = new User();
        // �����ϲ�ѯ����е�username/phone/email/gender��װ����User������
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        user.setAvatar(result.getAvatar());
        // �����µ�User����
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("�û����ݲ�����");
        }
        // �����user�в�ȫ���ݣ�uid
        user.setUid(uid);
        // �����user�в�ȫ���ݣ�modifiedUser(username)
        user.setModifiedUser(username);
        // �����user�в�ȫ���ݣ�modifiedTime(new Date())
        user.setModifiedTime(new Date());
        // ����userMapper��updateInfoByUid(User user)����ִ���޸ģ�����ȡ����ֵ
        Integer rows = userMapper.updateInfoByUid(user);

        // �ж����Ϸ��ص���Ӱ�������Ƿ�Ϊ1
        if (rows != 1) {
            // �ǣ��׳�UpdateException�쳣
            throw new UpdateException("�����û�����ʱ����δ֪��������ϵϵͳ����Ա");
        }

    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("�û����ݲ�����");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("�����û�ͷ�����δ֪���쳣");
        }
    }


    //����һ��MD5������
    private String getMD5Password(String Password, String salt) {
        //md5�����㷨�������μ���
        for (int i = 0; i < 3; i++) {
            Password = DigestUtils.md5DigestAsHex((salt + Password + salt).getBytes()).toUpperCase();
        }
        return Password;
    }
}
