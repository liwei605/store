package com.cqu.store.service.impl;

import com.cqu.store.entity.Discuss;
import com.cqu.store.mapper.DiscussMapper;
import com.cqu.store.mapper.UserMapper;
import com.cqu.store.service.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscussServiceImpl implements IDiscussService {

    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Discuss> searchByPid(Integer pid) {
        return discussMapper.searchByPid(pid);
    }

    @Override
    public Integer insertDiscuss(Integer uid, Integer pid, String discussion) {
        Date date = new Date();
        Discuss discuss = new Discuss();
        discuss.setUid(uid);
        discuss.setPid(pid);
        discuss.setDiscussion(discussion);
        discuss.setCreatedTime(date);
        discuss.setModifiedTime(date);
        String username = userMapper.findByUid(uid).getUsername();
        discuss.setCreatedUser(username);
        discuss.setModifiedUser(username);

        int ret = discussMapper.insertDiscuss(discuss);
        return ret;


    }


}
